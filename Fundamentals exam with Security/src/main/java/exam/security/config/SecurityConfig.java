package exam.security.config;

import exam.security.userDetails.UserEntityDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserEntityDetails userEntityDetails;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserEntityDetails userEntityDetails) {
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
                .antMatchers("/", "/users**").permitAll()
                .antMatchers("/home").hasRole("USER")
                .antMatchers("/products/add").hasRole("ADMIN")
                .antMatchers("/products/buy").hasRole("USER")
                .antMatchers("/products/buy/all").hasRole("USER")
                .and().formLogin().loginPage("/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password").failureUrl("/users/login/fail")
                .defaultSuccessUrl("/home").and().logout().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/unauthorized");
    }
}
