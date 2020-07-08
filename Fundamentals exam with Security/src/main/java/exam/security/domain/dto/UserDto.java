package exam.security.domain.dto;

import java.util.List;

public class UserDto extends BaseDto {

    private String username;
    private String password;
    private String email;
    private List<UserRoleDto> userRoles;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRoleDto> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleDto> userRoles) {
        this.userRoles = userRoles;
    }
}
