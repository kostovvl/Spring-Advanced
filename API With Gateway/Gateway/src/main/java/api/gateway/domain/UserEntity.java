package api.gateway.domain;

import java.util.Set;

public class UserEntity {

    public long id;
    public String username;
    public String password;
    public Set<UserEntityRole> roles;

    public UserEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<UserEntityRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserEntityRole> roles) {
        this.roles = roles;
    }
}
