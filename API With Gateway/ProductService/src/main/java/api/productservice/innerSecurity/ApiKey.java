package api.productservice.innerSecurity;

public class ApiKey {

    public String securityKey;

    public ApiKey() {
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
        System.out.println("Security Key Updated!");
    }
}
