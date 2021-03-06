package microservice.user.api;
import microservice.user.domian.UserRegisterBinding;
import microservice.user.domian.dto.UserDto;
import microservice.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final WebClient.Builder webClientBuilder;
    private final RestTemplate restTemplate;

    public UserController(UserService userService, WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.userService = userService;
        this.webClientBuilder = webClientBuilder;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/register")
    public RestTemplate add() {

       ResponseEntity<String> UserRegisterBindingJson = restTemplate.getForEntity("http://localhost:8080", String.class);

        System.out.println();


        return restTemplate;
    }

    @GetMapping("/get{id}")
    public UserDto getUsers(@PathVariable("id") Long id) {

        UserDto userDto = this.userService.findById(id);
        System.out.println();
        return userDto;
    }


}
