package spring.workshop.user.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.workshop.user.domain.UserDto;
import spring.workshop.user.repository.UserEntityRepository;
import spring.workshop.user.service.UserEntityService;

@Component
public class InitializeUsers implements CommandLineRunner {

    private final UserEntityRepository userEntityRepository;
    private final UserEntityService userEntityService;

    public InitializeUsers(UserEntityRepository userEntityRepository, UserEntityService userEntityService) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityService = userEntityService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userEntityRepository.count() == 0) {
            UserDto admin = new UserDto();
            admin.setUsername("lucho@example.com");
            admin.setPassword("topsecret");

            this.userEntityService.seedNewUser(admin);

            UserDto user = new UserDto();
            user.setUsername("user@example.com");
            user.setPassword("topsecret");

            this.userEntityService.seedNewUser(user);
        }
    }
}
