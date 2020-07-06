package spring.security.domain.binding;

import org.hibernate.validator.constraints.Length;

public class UserRegisterBinding {

    private String username;
    private String password;
    private String passwordConfirm;

    public UserRegisterBinding() {
    }

    @Length(min = 2, message = "Username must be at least 2 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2, message = "Username must be at least 2 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
