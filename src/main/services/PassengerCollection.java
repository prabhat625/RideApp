package main.services;

import main.models.Passenger;
import java.util.Set;
import java.util.HashSet;

public class PassengerCollection {
    Set<Passenger> passengerSet;
    private static PassengerCollection instance ;
    private PassengerCollection() {
        passengerSet = new HashSet<Passenger>();
    }
    public void addPassenger(Passenger passenger) {
        passengerSet.add(passenger);
    }
    public static PassengerCollection getInstance() {
        if (instance == null) {
            instance = new PassengerCollection();
        }
        return instance;
    }
    public boolean isValidPassenger(Passenger passenger) {
        return passenger == null || !passengerSet.contains(passenger) ? false : true;
    }
}
