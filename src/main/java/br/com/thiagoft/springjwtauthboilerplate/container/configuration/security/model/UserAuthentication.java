package br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model;

import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.AuthenticationRequest;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.AuthenticationResponse;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.SpringSecurityUser;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserAuthentication {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    public AuthenticationToken validateUser(AuthenticationRequest userForAuthentication) {

        if (isUserValid(userForAuthentication)) {
            return setSpringContextAuthentication(userForAuthentication);
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private boolean isUserValid(AuthenticationRequest userForAuthentication) {
        return userForAuthentication != null &&
                userForAuthentication.getUsername() != null &&
                userForAuthentication.getPassword() != null &&
                !userForAuthentication.getUsername().isEmpty() &&
                !userForAuthentication.getPassword().isEmpty();
    }

    private AuthenticationToken setSpringContextAuthentication(AuthenticationRequest userForAuthentication) {
        Authentication authentication =
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userForAuthentication.getUsername(),
                        userForAuthentication.getPassword()));

        setAuthenticationContext(authentication);

        return new AuthenticationToken(authenticationManager,
                userDetailsService,
                tokenUtils,
                userForAuthentication);
    }

    private void setAuthenticationContext(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        String token = request.getHeader(TokenUtils.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        SpringSecurityUser user = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset()))
        {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return new AuthenticationResponse(refreshedToken);
        }
        else
        {
            throw new RuntimeException("Not valid token.");
        }
    }
}
