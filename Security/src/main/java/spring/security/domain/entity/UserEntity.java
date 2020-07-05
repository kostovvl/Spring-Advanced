package spring.security.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    private Long id;
    private String name;
    private String password;
    private boolean isActive;
    List<UserAuthorityEntity> authorities;

    public UserEntity() {
    }

    public UserEntity(String name, String password, boolean isActive) {
        this.name = name;
        this.password = password;
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @OneToMany(mappedBy = "userEntity", targetEntity = UserAuthorityEntity.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<UserAuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<UserAuthorityEntity> authorities) {
        this.authorities = authorities;
    }
}
