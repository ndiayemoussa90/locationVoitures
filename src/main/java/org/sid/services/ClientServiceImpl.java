package org.sid.services;

import org.sid.auth.AuthenticateDetails;
import org.sid.auth.AuthenticateDetailsService;
import org.sid.entities.Authenticate;
import org.sid.repositories.AuthenticateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService {

    private final AuthenticateRepository authenticateRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticateDetailsService userDetailsService;

    @Autowired
    public ClientServiceImpl(
        AuthenticateRepository authenticateRepository,
        PasswordEncoder passwordEncoder,
        AuthenticateDetailsService userDetailsService
    ) {
        this.authenticateRepository = authenticateRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    private Authenticate getAuthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return null;
        }
        AuthenticateDetails authenticateDetails= (AuthenticateDetails) authentication.getPrincipal();
        return authenticateDetails.getAuthenticate();
    }


}
