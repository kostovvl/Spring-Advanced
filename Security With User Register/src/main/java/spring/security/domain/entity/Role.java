package spring.security.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private String name;
    private Set<UserEntity> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany()
    @JoinTable(name = "user_roles_users",
    joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public void addUser(UserEntity userEntity) {
        this.users.add(userEntity);
    }
}
