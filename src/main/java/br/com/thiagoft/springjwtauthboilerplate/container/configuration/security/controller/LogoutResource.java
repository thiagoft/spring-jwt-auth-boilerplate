package br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thiagofonseca.
 */
@RestController
@RequestMapping("logout")
public class LogoutResource {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> logout()
            throws AuthenticationException {

        return ResponseEntity.ok("Logout...");
    }

}
