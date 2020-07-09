package exam.security.domain.view;

public abstract class BaseView {

    private String id;

    public BaseView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
