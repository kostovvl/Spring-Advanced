package microservice.user.domian.dto;

public abstract class BaseDto {

    private Long id;

    public BaseDto() {
    }

    //May be necessary to put @Expose since this will be the object that will be unmarshaling(I think)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
