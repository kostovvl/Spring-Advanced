package api.gateway.web.client;

import api.gateway.config.Global;
import api.gateway.domain.UserEntity;
import api.gateway.innerSecurity.ApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate;
    private final ApiKey apiKey;


    @Autowired
    public UserClient(RestTemplate restTemplate, ApiKey apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public UserEntity registerUser(UserEntity userEntity) {
        System.out.println();
        return this.restTemplate.postForObject(Global.USER_SERVICE_URL + "/register/" + this.apiKey.getSecurityKey(),
                userEntity, UserEntity.class);
    }

    public UserEntity loginUser(String username) {
        return this.restTemplate.getForObject(Global.USER_SERVICE_URL + "/auth/" + username + "/" + this.apiKey.getSecurityKey(),
                UserEntity.class);
    }

}
