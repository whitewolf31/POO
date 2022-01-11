package app.errors;

public class InsufficientInventoryWeightException extends Exception{
    public InsufficientInventoryWeightException() {
        super("Insufficient invetory space. Buy something else or continue (Input number/N): ");
    }
}
