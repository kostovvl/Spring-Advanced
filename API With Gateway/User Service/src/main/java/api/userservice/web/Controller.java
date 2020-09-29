package api.userservice.web;

import api.userservice.domain.UserEntity;
import api.userservice.innerSecurity.ApiKey;
import api.userservice.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class Controller {

    private final UserEntityService userEntityService;
    private final ApiKey apiKey;

    @Autowired
    public Controller(UserEntityService userEntityService, ApiKey apiKey) {
        this.userEntityService = userEntityService;
        this.apiKey = apiKey;
    }

    @PostMapping("/register/{key}")
    public ResponseEntity<?>  register(@PathVariable(name = "key") String key,
                                @RequestBody UserEntity userEntity) {
        if (key.equals(this.apiKey.getSecurityKey())) {
        return new ResponseEntity<>(this.userEntityService.register(userEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/auth/{username}/{key}")
    public ResponseEntity<?> findByUsername(@PathVariable(name = "username") String username,
                                            @PathVariable(name = "key") String key) {
        try {
            if (key.equals(this.apiKey.getSecurityKey())) {
            UserEntity result = this.userEntityService.findByUsername(username);
            return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
