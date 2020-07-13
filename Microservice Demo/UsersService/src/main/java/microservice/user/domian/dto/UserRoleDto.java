package microservice.user.domian.dto;

public class UserRoleDto extends BaseDto {

    private String name;


    public UserRoleDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
