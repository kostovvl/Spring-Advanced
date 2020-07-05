package spring.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spring.security.domain.entity.UserAuthorityEntity;
import spring.security.domain.entity.UserEntity;
import spring.security.repository.UserEntityRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserEntityDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userEntity = this.userEntityRepository.findByName(username);
        return userEntity.map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User" + username + "not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getAuthorities()
                .stream()
                .map(this::getAuthorities)
                .collect(Collectors.toList())
        );
    }

    private GrantedAuthority getAuthorities(UserAuthorityEntity userAuthorityEntity) {
        return new SimpleGrantedAuthority(userAuthorityEntity.getName());
    }
}
