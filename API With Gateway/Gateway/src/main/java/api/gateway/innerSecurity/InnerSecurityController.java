package api.gateway.innerSecurity;

import api.gateway.config.Global;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InnerSecurityController {

    private final RestTemplate restTemplate;
    private final ApiKeyGenerator apiKeyGenerator;
    private final ApiKey apiKey;

    public InnerSecurityController(RestTemplate restTemplate, ApiKeyGenerator apiKeyGenerator, ApiKey apiKey) {
        this.restTemplate = restTemplate;
        this.apiKeyGenerator = apiKeyGenerator;
        this.apiKey = apiKey;
    }

    @Scheduled(cron = "1 10 * * * ? *")
    public void sendKey() {
        this.apiKeyGenerator.generateKey();
        this.restTemplate.postForObject(Global.USER_SERVICE_URL + "/security",
                this.apiKey.getSecurityKey(), String.class);
    }
}
