package br.com.thiagoft.springjwtauthboilerplate.module.core.dao;

import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;

public interface UserRepository {
    User findByUsername(String username);
}
