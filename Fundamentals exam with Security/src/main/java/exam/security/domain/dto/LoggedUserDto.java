package exam.security.domain.dto;

public class LoggedUserDto extends BaseDto {

    private String username;

    public LoggedUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
