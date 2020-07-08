package exam.security.domain.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;


public class UserRegisterBinding extends BaseBinding {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterBinding() {
    }

    @Length(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Length(min = 1, message = "Email can not be empty!")
    @Email(message = "Please enter valid email!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
