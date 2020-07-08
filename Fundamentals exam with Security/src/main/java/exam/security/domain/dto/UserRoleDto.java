package exam.security.domain.dto;

public class UserRoleDto extends BaseDto {

    private String name;
    private UserDto user;

    public UserRoleDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
