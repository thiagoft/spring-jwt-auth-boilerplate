package br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.controller;

import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.UserAuthentication;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.AuthenticationRequest;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
@Scope("request")
public class AuthenticationResource {

    private UserAuthentication userAuthentication;

    @Autowired
    public AuthenticationResource(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;

    }

    @PostMapping
    public ResponseEntity<?> getToken(@RequestBody AuthenticationRequest userForAuthentication) {
        AuthenticationResponse authenticationResponse = userAuthentication.validateUser(userForAuthentication).getAuthenticationResponse();

        return ResponseEntity.ok(authenticationResponse);
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        try {
            AuthenticationResponse authenticationResponse = userAuthentication.refreshToken(request);
            return ResponseEntity.ok(authenticationResponse);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
