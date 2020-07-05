package spring.security.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.security.domain.entity.UserAuthorityEntity;
import spring.security.domain.entity.UserEntity;
import spring.security.repository.UserAuthorityRepository;
import spring.security.repository.UserEntityRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Component
public class InitializeUsers implements CommandLineRunner {

    private final UserEntityRepository userEntityRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    public InitializeUsers(UserEntityRepository userEntityRepository,
                           UserAuthorityRepository userAuthorityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (this.userEntityRepository.count() != 0) {
            return;
        }


        UserEntity user = new UserEntity("user", passwordEncoder.encode("user"), true);

        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();
        userAuthorityEntity.setName("ROLE_USER");
        userAuthorityEntity.setUserEntity(user);

        user.setAuthorities(List.of(userAuthorityEntity));

        this.userEntityRepository.saveAndFlush(user);


        UserEntity admin = new UserEntity("admin", passwordEncoder.encode("admin"), true);

        UserAuthorityEntity userUserAuthorityEntity = new UserAuthorityEntity();
        userUserAuthorityEntity.setName("ROLE_USER");
        userUserAuthorityEntity.setUserEntity(admin);

        UserAuthorityEntity userAdminAuthorityEntity = new UserAuthorityEntity();
        userAdminAuthorityEntity.setName("ROLE_ADMIN");
        userAdminAuthorityEntity.setUserEntity(admin);

        user.setAuthorities(List.of(userUserAuthorityEntity, userAdminAuthorityEntity));

        this.userEntityRepository.saveAndFlush(admin);

    }
}
