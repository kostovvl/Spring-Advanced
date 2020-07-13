package spring.workshop.user.domain;

import javax.validation.constraints.NotBlank;

public class UserDto {

    private String username;
    private String password;


    public UserDto() {
    }


    @NotBlank(message = "E-mail can not be blank!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Password can not be blank!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
