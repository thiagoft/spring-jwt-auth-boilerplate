package br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model;

import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.AuthenticationRequest;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.AuthenticationResponse;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.util.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthenticationToken {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private TokenUtils tokenUtils;
    private AuthenticationRequest userForAuthentication;

    public AuthenticationToken(AuthenticationManager authenticationManager,
                               UserDetailsService userDetailsService,
                               TokenUtils tokenUtils,
                               AuthenticationRequest userForAuthentication) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
        this.userForAuthentication = userForAuthentication;
    }

    private UserDetails getSpringUser() {
        UserDetails springSecurityUser = this.userDetailsService.loadUserByUsername(userForAuthentication.getUsername());
        return springSecurityUser;
    }

    private String generateToken() {
        UserDetails springSecurityUser = getSpringUser();
        return this.tokenUtils.generateToken(springSecurityUser);
    }

    public AuthenticationResponse getAuthenticationResponse() {
        return new AuthenticationResponse(generateToken());
    }

}
