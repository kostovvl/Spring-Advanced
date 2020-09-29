package api.userservice.config;

import api.userservice.innerSecurity.ApiKey;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppBeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ApiKey apiKey() {
        return new ApiKey();
    }

}
