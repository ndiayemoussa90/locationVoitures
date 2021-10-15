package org.sid.auth;

import org.sid.entities.Authenticate;
import org.sid.repositories.AuthenticateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateDetailsService implements UserDetailsService {

    private final AuthenticateRepository authenticateRepository;

    @Autowired
    public AuthenticateDetailsService(AuthenticateRepository authenticateRepository) {
        this.authenticateRepository = authenticateRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Authenticate> authenticateOptional = authenticateRepository.findByUsername(username);
        authenticateOptional.orElseThrow(
            () -> new UsernameNotFoundException(String.format("Username %s Not found",username))
        );
        return authenticateOptional.map(AuthenticateDetails::new).get();
    }

}
