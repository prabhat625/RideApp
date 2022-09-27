package main.exceptions;

public class DriverNotFoundException extends Exception {
    public DriverNotFoundException(String message) {
        super(message);
    }
    public DriverNotFoundException () {
        super("Driver Not Found");
    }
}
