package api.registration;

public class SuccessRegistration {
    private Integer id;
    private String token;
    public SuccessRegistration(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public SuccessRegistration() {
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
