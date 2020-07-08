package exam.security.userDetails;

import exam.security.domain.dto.UserDto;
import exam.security.domain.entity.UserEntity;
import exam.security.service.UserService;
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
public class UserEntityDetails implements UserDetailsService {

    private final UserService userService;

    public UserEntityDetails(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userEntityOpt = this.userService.findByUsername(username);
        if (userEntityOpt == null) {
            throw new UsernameNotFoundException("User" + username + "does not exist!");
        }

        String name = userEntityOpt.getUsername();
        String password = userEntityOpt.getPassword();
        List<GrantedAuthority> authorities = userEntityOpt.getUserRoles().stream()
                .map(a -> new SimpleGrantedAuthority(a.getName()))
                .collect(Collectors.toList());

        return  new User(name, password, authorities);
    }
}
