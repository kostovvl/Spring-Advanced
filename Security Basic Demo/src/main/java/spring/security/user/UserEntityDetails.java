package spring.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spring.security.domain.entity.UserEntity;
import spring.security.domain.entity.UserRole;
import spring.security.repository.UserEntityRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserEntityDetails implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityDetails(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userEntityOpt = this.userEntityRepository.findByUsername(username);

        return userEntityOpt
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("Username" + username + "does not exist!"));
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

    private GrantedAuthority map(UserRole role) {
        return new SimpleGrantedAuthority(role.getName());
    }
}
