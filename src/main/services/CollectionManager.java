package main.services;

import main.models.Passenger;

public class CollectionManager {
    public static RideCollection RIDE_COLLECTION = RideCollection.getInstance();
    public static PassengerCollection PASSENGER_COLLECTION = PassengerCollection.getInstance();
    public static DriverCollection DRIVER_COLLECTION = DriverCollection.getInstance();
    private CollectionManager() {;}
}
