package app.errors;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String command) {
        super(command);
    }
}
