package spring.workshop.user.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {

    private Long id;
    private String role;
    private UserEntity userEntity;

    public UserRole() {
    }

    public UserRole(String role) {
        this.role = role;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
