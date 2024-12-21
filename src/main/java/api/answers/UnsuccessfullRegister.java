package api.answers;

public class UnsuccessfullRegister {
    private String error;

    public UnsuccessfullRegister(String error) {
        this.error = error;
    }

    public UnsuccessfullRegister() {
    }

    public String getError() {
        return error;
    }
}
