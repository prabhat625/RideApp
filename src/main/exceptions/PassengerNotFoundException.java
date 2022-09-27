package main.exceptions;

public class PassengerNotFoundException extends Exception{
    public PassengerNotFoundException(String message) {
        super(message);
    }
    public PassengerNotFoundException () {
        super("Passenger Not Found");
    }
}
