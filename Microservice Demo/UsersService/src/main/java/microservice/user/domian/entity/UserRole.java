package microservice.user.domian.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity {

    private String name;
    private UserEntity user;

    public UserRole() {
    }

    public UserRole(String name, UserEntity user) {
        this.name = name;
        this.user = user;
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
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
