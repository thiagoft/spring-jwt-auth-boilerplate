package br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.service;

import br.com.thiagoft.springjwtauthboilerplate.container.configuration.security.model.dto.SpringSecurityUser;
import br.com.thiagoft.springjwtauthboilerplate.module.core.dao.UserRepository;
import br.com.thiagoft.springjwtauthboilerplate.module.core.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No valid user found with username '%s'.", username));
        } else {
            return new SpringSecurityUser(
                    user.getUsername(),
                    user.getEncodedPassword(),
                    user.getEmail(),
                    user.getLastPasswordReset(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN")
            );
        }
    }

}
