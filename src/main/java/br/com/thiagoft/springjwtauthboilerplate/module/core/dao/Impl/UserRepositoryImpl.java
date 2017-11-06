package br.com.thiagoft.springjwtauthboilerplate.module.core.dao.Impl;

import br.com.thiagoft.springjwtauthboilerplate.module.core.dao.UserRepository;
import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        return listOfUsers.get(0);
    }
}
