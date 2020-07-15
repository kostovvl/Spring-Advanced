package spring.workshop.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.workshop.user.domain.UserEntity;
import spring.workshop.user.repository.UserEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final UserEntityRepository userEntityRepository;

    public UserDetailsServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = this.userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Shit!"));

        System.out.println();

        String userDetailsUsername = userEntity.getUsername();
        String userDetailsPassword = userEntity.getPassword();
        List<GrantedAuthority> roles = userEntity.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());

        User user = new User(userDetailsUsername, userDetailsPassword, roles);
        return new User(userDetailsUsername, userDetailsPassword, roles);
    }
}
