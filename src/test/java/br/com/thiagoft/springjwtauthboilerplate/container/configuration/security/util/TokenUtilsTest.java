package br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.util;

import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.SpringSecurityUser;
import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TokenUtilsTest {

    private TokenUtils tokenUtils;
    private UserDetails userDetails;

    @Before
    public void init() {
        User user = new User("username", "password");

        userDetails = new SpringSecurityUser(
                user.getUsername(),
                user.getEncodedPassword(),
                user.getEmail(),
                user.getLastPasswordReset(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));

        tokenUtils = new TokenUtils();
    }

    @Test
    public void shouldReturnValidToken() {
        final String token = tokenUtils.generateToken(userDetails);

        assertTrue(tokenUtils.validateToken(token,userDetails));
    }

    @Test
    public void shouldReturnFalseForInvalidToken() {
        User invalidUser = new User("wronguser", "wrongpassword");

        UserDetails invalidUserDetails = new SpringSecurityUser(
                invalidUser.getUsername(),
                invalidUser.getEncodedPassword(),
                invalidUser.getEmail(),
                invalidUser.getLastPasswordReset(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));

        final String token = tokenUtils.generateToken(userDetails);

        assertFalse(tokenUtils.validateToken(token,invalidUserDetails));
    }

    @Test
    public void shouldReturnTheUsernameFromToken() {
        final String token = tokenUtils.generateToken(userDetails);

        assertEquals("username",tokenUtils.getUsernameFromToken(token));
    }

    @Test
    public void shouldReturnCreatedDateTimeFromToken() {
        final String token = tokenUtils.generateToken(userDetails);

        assertTrue(tokenUtils.getCreatedDateFromToken(token) instanceof Date);
    }

    @Test
    public void shouldReturnExpirationDateFromToken() {
        final String token = tokenUtils.generateToken(userDetails);

        final Date now = new Date();
        final Date expirationDateFromToken = tokenUtils.getExpirationDateFromToken(token);

        assertThat(DateUtil.timeDifference(expirationDateFromToken, now)).isCloseTo(3600000L, within(2000l));
    }

    @Test
    public void shouldReturnFalseForExpiredToken() {

    }

    @Test
    public void shouldReturnFalseForANonRefreshableToken() {
        final String token = tokenUtils.generateToken(userDetails);

        assertFalse(tokenUtils.canTokenBeRefreshed(token,new Date()));
    }

    @Test
    public void shouldReturnAValidRefreshedTokenFromAValidToken() {
        final String token = tokenUtils.generateToken(userDetails);

        final String refreshedToken = tokenUtils.refreshToken(token);

        assertTrue(tokenUtils.validateToken(refreshedToken,userDetails));
    }
}