package api.userservice.web;

import api.userservice.innerSecurity.ApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final ApiKey apiKey;

    @Autowired
    public SecurityController(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    @PostMapping("users-service")
    public void writeKey(String key) {
        this.apiKey.setSecurityKey(key);
    }

}
