package spring.security.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.security.domain.entity.User;
import spring.security.domain.entity.UserRole;
import spring.security.repository.UserRepository;
import spring.security.repository.UserRoleRepository;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class InitializeUsers implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public InitializeUsers(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (this.userRepository.count() != 0) {
            return;
        }

       UserRole userRoleUser = new UserRole("USER");
        User user  = new User("user", passwordEncoder.encode("user"), true);
        user.setRoles(Set.of(userRoleUser));
        userRoleUser.setUser(user);
        this.userRepository.saveAndFlush(user);


        UserRole adminRoleUser = new UserRole("USER");
        UserRole adminRoleAdmin = new UserRole("ADMIN");
        User admin  = new User("admin", passwordEncoder.encode("user"), true);

        adminRoleUser.setUser(admin);
        adminRoleAdmin.setUser(admin);
        admin.setRoles(Set.of(adminRoleUser, adminRoleAdmin));
        this.userRepository.saveAndFlush(admin);

    }
}
