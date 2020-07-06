package spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.security.user.UserEntityService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserEntityService userEntityService;


    public SecurityConfig(PasswordEncoder passwordEncoder, UserEntityService userEntityService) {
        this.passwordEncoder = passwordEncoder;
        this.userEntityService = userEntityService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userEntityService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/users**").permitAll()
                .antMatchers("/home").hasRole("USER")
                .antMatchers("/home").hasRole("ADMIN");

    }
}
