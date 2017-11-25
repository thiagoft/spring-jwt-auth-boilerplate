package br.com.thiagoft.springjwtauthboilerplate.module.core.dao;

import br.com.thiagoft.springjwtauthboilerplate.SpringJwtAuthBoilerplateApplicationTests;
import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
public class UserRepositoryTest extends SpringJwtAuthBoilerplateApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void returnACorrectUserFromRepository() {
        User mockUser = new User("username","password","username@user.com");
        User userFromRepository = userRepository.findByUsername("username");

        assertEquals(mockUser,userFromRepository);
    }

    @Test
    public void returnNullFromAnIncorrectUserFromRepository() {
        User mockUser = new User("username","password","username@user.com");
        User userFromRepository = userRepository.findByUsername("wronguser");

        assertNotEquals(mockUser,userFromRepository);
    }

}
