package spring.security.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntity {

    private String name;
    private UserEntity userEntity;

    public UserRole() {
    }

    public UserRole(String name) {
        this.name = name;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
