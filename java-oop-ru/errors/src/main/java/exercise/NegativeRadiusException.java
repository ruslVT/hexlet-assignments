package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {

    private String message;

    public NegativeRadiusException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
// END
