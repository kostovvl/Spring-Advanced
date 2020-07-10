package microservice.user.domian.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private boolean isActive;
    private List<UserRole> roles;

    public UserEntity() {
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "is_active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @OneToMany(mappedBy = "user", targetEntity = UserRole.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

}
