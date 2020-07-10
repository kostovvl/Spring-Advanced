package microservice.user.api;
import microservice.user.domian.dto.UserDto;
import microservice.user.repository.UserRepository;
import microservice.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public RestTemplate add(RestTemplate template) {

        UserDto userDto = template.getForObject("localhost:8081/users/register", UserDto.class);

        this.userService.seedNewUser(userDto);

        return template;
    }


}
