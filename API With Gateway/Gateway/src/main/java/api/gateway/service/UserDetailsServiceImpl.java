package api.gateway.service;

import api.gateway.domain.UserEntity;
import api.gateway.web.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Client client;

    @Autowired
    public UserDetailsServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.client.loginUser(username);

        String userNameDetail = userEntity.getUsername();
        String passwordDetail = userEntity.getPassword();
        List<GrantedAuthority> roles = userEntity.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());

        return new User(userNameDetail, passwordDetail, roles);
    }
}

