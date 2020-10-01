package api.productservice.configuration;

import api.productservice.innerSecurity.ApiKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeanConfig {

    @Bean
    public ApiKey apiKey() {
        return new ApiKey();
    }

}
