package spring.security.domain.dto;

import spring.security.domain.entity.Role;

import java.util.Set;

public class UserEntityDto extends BaseDto {

    private String username;
    private String password;
    private boolean isActive;
    private Set<Role> roles;

    public UserEntityDto() {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
