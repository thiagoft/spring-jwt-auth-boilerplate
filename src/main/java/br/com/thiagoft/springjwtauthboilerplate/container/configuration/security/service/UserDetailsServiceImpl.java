package br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.service;

import br.com.thiagoft.springjwtauthboilerplate.module.core.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No valid user found with username '%s'.", username));
        } else {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
            return userBuilder
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles("ROLE_USER,ROLE_ADMIN")
                    .build();
        }
    }

}
