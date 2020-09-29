package api.gateway.innerSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyGenerator {

    private final ApiKey apiKey;

    @Autowired
    public ApiKeyGenerator(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void generateKey() {
        String key = "SecretKey";
        this.apiKey.setSecurityKey(key);
    }
}
