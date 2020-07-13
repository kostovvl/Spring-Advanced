package spring.workshop.user.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    private Long id;
    private String username;
    private String password;
    private List<UserRole> roles;

    public UserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @OneToMany(mappedBy = "userEntity", targetEntity = UserRole.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
