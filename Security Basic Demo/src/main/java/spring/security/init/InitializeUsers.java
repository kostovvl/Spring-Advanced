package spring.security.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.security.domain.entity.UserEntity;
import spring.security.domain.entity.UserRole;
import spring.security.repository.UserEntityRepository;

import java.util.List;
import java.util.Set;

@Component
public class InitializeUsers implements CommandLineRunner {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public InitializeUsers(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.userEntityRepository.count() > 0) {
            return;
        }

        UserEntity user = new UserEntity("user", passwordEncoder.encode("user"), true);
        UserRole userUserRoleUser = new UserRole("ROLE_USER");
        userUserRoleUser.setUserEntity(user);

        user.setRoles(Set.of(userUserRoleUser));

        UserEntity admin = new UserEntity("admin", passwordEncoder.encode("admin"), true);
        UserRole userAdminRoleUser = new UserRole("ROLE_USER");
        UserRole userAdminRoleAdmin = new UserRole("ROLE_ADMIN");

        userAdminRoleUser.setUserEntity(admin);
        userAdminRoleAdmin.setUserEntity(admin);

        admin.setRoles(Set.of(userAdminRoleUser, userAdminRoleAdmin));

        this.userEntityRepository.saveAll(List.of(user, admin));

    }
}
