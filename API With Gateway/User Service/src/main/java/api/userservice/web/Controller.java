package api.userservice.web;

import api.userservice.domain.UserEntity;
import api.userservice.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class Controller {

    private final UserEntityService userEntityService;

    @Autowired
    public Controller(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/register")
    public UserEntity  register(@RequestBody UserEntity userEntity) {
        return this.userEntityService.register(userEntity);
    }

    @GetMapping("/auth/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable(name = "username") String username) {
        try {
            UserEntity result = this.userEntityService.findByUsername(username);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
