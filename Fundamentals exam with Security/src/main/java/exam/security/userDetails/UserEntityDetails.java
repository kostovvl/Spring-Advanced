package exam.security.userDetails;

import exam.security.domain.entity.UserEntity;
import exam.security.repository.UserEntityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserEntityDetails implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public UserEntityDetails(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntityOpt = this.userEntityRepository.findByUsername(username).orElse(null);
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
