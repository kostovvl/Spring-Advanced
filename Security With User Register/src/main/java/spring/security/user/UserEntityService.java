package spring.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spring.security.domain.entity.Role;
import spring.security.domain.entity.UserEntity;
import spring.security.repository.UserEntityRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserEntityService implements UserDetailsService {

    private final UserEntityRepository userRepository;

    public UserEntityService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userEntityOpt = this.userRepository.findByUsername(username);

        return userEntityOpt
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("Username" + username + "not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoles()
                .stream()
                .map(this::map)
                .collect(Collectors.toList())
        );
    }

    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority(role.getName());
    }
}
