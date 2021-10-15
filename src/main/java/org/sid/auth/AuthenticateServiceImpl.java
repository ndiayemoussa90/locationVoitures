package org.sid.auth;

import org.sid.datas.RegistrationRequest;
import org.sid.datas.UserResponse;
import org.sid.entities.User;
import org.sid.entities.Authenticate;
import org.sid.repositories.AuthenticateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final AuthenticateRepository authenticateRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticateDetailsService userDetailsService;

    @Autowired
    public AuthenticateServiceImpl(
        AuthenticateRepository authenticateRepository,
        PasswordEncoder passwordEncoder,
        AuthenticateDetailsService userDetailsService
    ) {
        this.authenticateRepository = authenticateRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public Authenticate getAuthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return null;
        }
        AuthenticateDetails authenticateDetails= (AuthenticateDetails) authentication.getPrincipal();
        return authenticateDetails.getAuthenticate();
    }

    @Override
    public Authenticate save(String username, String password, String roles, User user) {
        Authenticate authenticate = new Authenticate(username, passwordEncoder.encode(password), roles, user);
        authenticate = authenticateRepository.save(authenticate);
        return authenticate;
    }

    @Override
    public Authenticate save(RegistrationRequest registrationRequest) {
        final Authenticate author = getAuthenticate();
        Authenticate authenticateUsername = authenticateRepository.findFirstByUsername(registrationRequest.getUsername());
        if(authenticateUsername != null){
            return null;
        }
        Authenticate authenticate = new Authenticate(
                registrationRequest.getUsername(), passwordEncoder.encode(registrationRequest.getPasswordConfirmed()), registrationRequest.getRoles(),
                new User(
                        registrationRequest.getFirstName(), registrationRequest.getLastName(),
                        registrationRequest.getAddress(), registrationRequest.getPhoneNumber(),
                        registrationRequest.getCni(), (author == null) ? null : author.getUser()
                )
        );
        authenticate = authenticateRepository.save(authenticate);
        return authenticate;
    }

    @Override
    public boolean updateById(long id, RegistrationRequest registrationRequest) {
        final Authenticate author = getAuthenticate();
        Authenticate authenticate = authenticateRepository.getUserById(id, author.getUser()).orElseThrow(
            () -> new IllegalStateException("Le user id " + id + " n'existe pas.")
        );
        //
        Authenticate authenticateUsername = authenticateRepository.findFirstByUsername(registrationRequest.getUsername());
        if( (authenticateUsername != null) && !authenticateUsername.getUsername().equalsIgnoreCase(authenticate.getUsername())){
            return false;
        }
        //
        if( !registrationRequest.getPassword().isEmpty() && !registrationRequest.getPassword().equalsIgnoreCase("undefined")){
            authenticate.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        }
        //
        authenticate.setUsername(registrationRequest.getUsername());
        User newUser = authenticate.getUser();
        newUser.setFirstName(registrationRequest.getFirstName());
        newUser.setLastName(registrationRequest.getLastName());
        newUser.setPhoneNumber(registrationRequest.getPhoneNumber());
        newUser.setCni(registrationRequest.getCni());
        authenticate.setUser(newUser);
        authenticateRepository.save(authenticate);
        //
        return true;
    }

    @Override
    public void deleteById(long id) {
        Authenticate authenticate = authenticateRepository.findById(id).orElseThrow(
            () -> new IllegalStateException("Le user id " + id + " n'existe pas.")
        );
        User newUser = authenticate.getUser();
        newUser.setStatusUser((byte)3);
        newUser.setUpdatedAt(new Date());
        authenticate.setUser(newUser);
        authenticate.setAccountNonLocked(false);
        authenticate.setEnabled(false);
        authenticateRepository.save(authenticate);
    }

    @Override
    public List<UserResponse> listUsers() {
        List<UserResponse> userResponse = new ArrayList<>();
        //
        authenticateRepository.allUsers().forEach(auth ->
            userResponse.add(new UserResponse(
                auth.getId(),  auth.getUser().getFirstName(), auth.getUser().getLastName(),
                auth.getUser().getAddress(), auth.getUser().getPhoneNumber(),
                auth.getUser().getCni(), auth.getUsername()
            ))
        );
        return userResponse;
    }

    @Override
    public List<UserResponse> listUsers(String roles) {
        List<UserResponse> userResponse = new ArrayList<>();
        //
        authenticateRepository.listUsersByRoles(roles).forEach(auth ->
            userResponse.add(new UserResponse(
                auth.getId(),  auth.getUser().getFirstName(), auth.getUser().getLastName(),
                auth.getUser().getAddress(), auth.getUser().getPhoneNumber(),
                auth.getUser().getCni(), auth.getUsername()
            ))
        );
        return userResponse;
    }

    @Override
    public List<UserResponse> listUsers(String roles, User user) {

        List<UserResponse> userResponse = new ArrayList<>();
        //
        authenticateRepository.listUsersByRolesAndUserCreated(roles, user).forEach(auth ->
            userResponse.add(new UserResponse(
                auth.getId(),  auth.getUser().getFirstName(), auth.getUser().getLastName(),
                auth.getUser().getAddress(), auth.getUser().getPhoneNumber(),
                auth.getUser().getCni(), auth.getUsername()
            ))
        );
        return userResponse;
    }

    @Override
    public List<UserResponse> listUsers(User user) {
        List<UserResponse> userResponse = new ArrayList<>();
        //
        authenticateRepository.listUsers(user).forEach(auth ->
            userResponse.add(new UserResponse(
                auth.getId(),  auth.getUser().getFirstName(),
                auth.getUser().getLastName(), auth.getUser().getAddress(),
                auth.getUser().getPhoneNumber(), auth.getUser().getCni()
            ))
        );
        return userResponse;
    }

    @Override
    public List<UserResponse> listClients() {
        List<UserResponse> userResponse = new ArrayList<>();
        //
        authenticateRepository.getClients().forEach( client ->
                userResponse.add(new UserResponse(
                        client.getId(),  client.getUser().getFirstName(), client.getUser().getLastName(),
                        client.getUser().getAddress(), client.getUser().getPhoneNumber(),
                        client.getUser().getCni(), client.getUsername()
                ))
        );
        //
        return userResponse;
    }




    @Override
    public void login(Authenticate authenticate) {
        User newUser = authenticate.getUser();
        newUser.setLastConnectedAt(new Date());
        newUser.setUpdatedAt(new Date());
        authenticate.setUser(newUser);
        authenticateRepository.save(authenticate);
    }

    @Override
    public short count() {
        return (short) authenticateRepository.count();
    }

    
}
