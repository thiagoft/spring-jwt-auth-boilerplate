package br.com.thiagoft.springjwtauthboilerplate.module.core.dao;

import br.com.thiagoft.springjwtauthboilerplate.SpringJwtAuthBoilerplateApplicationTests;
import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UserRepositoryTest extends SpringJwtAuthBoilerplateApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void returnsACorrectUserFromRepository() {
        User mockUser = new User("username","password","username@user.com");
        User userFromRepository = userRepository.findByUsername("username");

        assertEquals(mockUser,userFromRepository);
    }

}
