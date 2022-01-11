package app.errors;

public class InsufficientCoinsException extends Exception {
    public InsufficientCoinsException() {
        super("Insufficient coins. But something else or continue (Input number/N): ");
    }
}
