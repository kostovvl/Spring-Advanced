package api.gateway.web;

import api.gateway.config.Global;
import api.gateway.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Client {

    private final RestTemplate restTemplate;


    @Autowired
    public Client(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserEntity registerUser(UserEntity userEntity) {
        return this.restTemplate.postForObject(Global.USER_SERVICE_URL + "/register", userEntity, UserEntity.class);
    }

    public UserEntity loginUser(String username) {
        return this.restTemplate.getForObject(Global.USER_SERVICE_URL + "/auth/" + username, UserEntity.class);
    }

}
