package api.gateway.web;

import api.gateway.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final Client client;

    @Autowired
    public UserController(Client client) {
        this.client = client;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity userEntity) {
        UserEntity result = this.client.registerUser(userEntity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
