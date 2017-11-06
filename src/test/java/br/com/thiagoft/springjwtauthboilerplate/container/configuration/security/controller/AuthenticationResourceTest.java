package br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.controller;

import br.com.thiagoft.springjwtauthboilerplate.SpringJwtAuthBoilerplateApplicationTests;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.SpringSecurityUser;
import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.util.TokenUtils;
import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AuthenticationResourceTest extends SpringJwtAuthBoilerplateApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Gson gson;

    @Autowired
    TokenUtils tokenUtils;

    private String token;

    @Before
    public void initAValidToken() {
        UserDetails userDetails = new SpringSecurityUser("username","password");

        token = tokenUtils.generateToken(userDetails);
    }

    @Test
    public void shouldReturnValidToken() throws Exception {
        User validUser = new User("username", "password");

        this.mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(validUser)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAccessDeniedForInvalidTokenAndInvalidUser() throws Exception {
        User invalidUser = new User("wronguser", "wrongpassword");

        this.mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(invalidUser)))
                .andExpect(status().is4xxClientError())
                .andExpect(status().reason("Access Denied"));
    }

    @Test
    public void shouldReturnAccessDeniedForInvalidUserAndValidToken() throws Exception {
        User invalidUser = new User("wronguser", "wrongpassword");

        this.mockMvc.perform(post("/auth")
                .header(TokenUtils.tokenHeader,token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(invalidUser)))
                .andExpect(status().is4xxClientError())
                .andExpect(status().reason("Access Denied"));
    }

    @Test
    public void shouldReturnNotFoundFromInvalidPathAuthorized() throws Exception {
        this.mockMvc.perform(get("/wrongPath")
                .header(TokenUtils.tokenHeader,token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnAccessDeniedFromInvalidPathUnauthorized() throws Exception {
        this.mockMvc.perform(get("/wrongPath"))
                .andExpect(status().reason("Access Denied"));
    }
}
