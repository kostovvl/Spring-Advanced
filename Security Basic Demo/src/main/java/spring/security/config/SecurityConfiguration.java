package spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.security.user.UserEntityDetails;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   private final PasswordEncoder passwordEncoder;
   private final UserDetailsService userEntityDetails;

    public SecurityConfiguration(PasswordEncoder passwordEncoder, UserEntityDetails userEntityDetails) {
        this.passwordEncoder = passwordEncoder;
        this.userEntityDetails = userEntityDetails;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userEntityDetails)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .and()
                .formLogin();
    }
}
