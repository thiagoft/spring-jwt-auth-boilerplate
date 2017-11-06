package br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertTrue;

public class UserTest {

    private User user;

    @Before
    public void mockingAnUser() {
        user = new User("username","password");
    }

    @Test
    public void mustReturnAnEncryptedPasswordByBCrypt() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        assertTrue(bCryptPasswordEncoder.matches("password",user.getEncodedPassword()));
    }

}
