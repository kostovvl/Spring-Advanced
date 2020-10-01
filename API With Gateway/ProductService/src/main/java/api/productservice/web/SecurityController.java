package api.productservice.web;

import api.productservice.innerSecurity.ApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping()
    public void writeKey(@RequestBody String apiKey) {
        this.apiKey.setSecurityKey(apiKey);
    }

}
