package br.com.thiagoft.springjwtauthboilerplate.module.core.dao;

import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
