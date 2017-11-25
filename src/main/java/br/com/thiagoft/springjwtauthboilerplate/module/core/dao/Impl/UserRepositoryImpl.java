package br.com.thiagoft.springjwtauthboilerplate.module.core.dao.Impl;

import br.com.thiagoft.springjwtauthboilerplate.module.core.dao.UserRepository;
import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    List<User> listOfUsers = new ArrayList<>();

    public UserRepositoryImpl() {
        listOfUsers.add(new User("username",
                "password",
                "username@user.com"));
    }

    @Override
    public User findByUsername(String username) {
        try {
            return listOfUsers.stream().findAny().filter(user -> user.getUsername().equals(username)).get();
        } catch(NoSuchElementException noElement) {
            noElement.printStackTrace();
            return new User();
        }
    }
}
