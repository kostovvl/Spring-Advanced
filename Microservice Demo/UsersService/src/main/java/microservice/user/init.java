package microservice.user;

import microservice.user.domian.dto.UserDto;
import microservice.user.domian.entity.UserEntity;
import microservice.user.repository.UserRepository;
import microservice.user.service.UserService;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class init implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;

    public init(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        String username = new String();
        String password = new String();

        if (this.userRepository.count() < 10) {
            for (int i = 0; i < 10; i++) {
                username = username + String.valueOf(i);
                password = password + String.valueOf(i);

                UserDto userDto = new UserDto();
                userDto.setUsername(username);
                userDto.setPassword(password);
                userDto.setActive(true);

                this.userService.seedNewUser(userDto);
            }
        }

    }
}
