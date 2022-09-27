package main.services;

import main.models.City;
import main.models.Passenger;
import main.models.Ride;
import main.models.Driver;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RideCollection {
    private Set<Ride> allRides;
    private Set<Ride> activeRides;
    private Map<Driver, Set<Ride>> driverToRidesMap;
    private Map<Passenger, Set<Ride>> passengerToRidesMap;
    private Map<City, Set<Ride>> sourceCityToRidesMap;

    private static RideCollection rideCollectionInstance;

    private RideCollection() {
        allRides = new HashSet<Ride>();
        activeRides = new HashSet<Ride>();
        driverToRidesMap = new HashMap<Driver, Set<Ride>>();
        passengerToRidesMap = new HashMap<Passenger, Set<Ride>>();
        sourceCityToRidesMap = new HashMap<City, Set<Ride>>();
    }

    public static RideCollection getInstance() {
        if (rideCollectionInstance == null) {
            rideCollectionInstance = new RideCollection();
        }
        return rideCollectionInstance;
    }

    public Set<Ride> getRidesByDriver(Driver driver) {
        return driverToRidesMap.getOrDefault(driver, new HashSet<Ride>());
    }

    public Set<Ride> getRidesByPassenger(Passenger passenger) {
        return passengerToRidesMap.getOrDefault(passenger, new HashSet<Ride>());
    }

    public Set<Ride> getRidesBySourceCity(City city) {
        return sourceCityToRidesMap.getOrDefault(city, new HashSet<Ride>());
    }

    public boolean addNewOfferRide(Ride ride) {
        driverToRidesMap.putIfAbsent(ride.getVehicle().getDriver(), new HashSet<>());
        sourceCityToRidesMap.putIfAbsent(ride.getSource(), new HashSet<>());
        return  allRides.add(ride) &&
                getRidesByDriver(ride.getVehicle().getDriver()).add(ride) &&
                getRidesBySourceCity(ride.getSource()).add(ride);
    }

    public boolean addNewActiveRide(Ride ride, Passenger passenger) {
        activeRides.add(ride);
        passengerToRidesMap.putIfAbsent(passenger, new HashSet<>());
        return activeRides.add(ride) && getRidesByPassenger(passenger).add(ride);
    }

    public Map<City, Set<Ride>> getRidesGraph() {
        return this.sourceCityToRidesMap;
    }

    public Map<Passenger, Set<Ride>> getPassengerToRidesMap(){
        return this.passengerToRidesMap;
    }

    public Map<Driver, Set<Ride>> getDriverToRidesMap(){
        return this.driverToRidesMap;
    }

    public boolean activeRideEnd(Ride ride) {
        return activeRides.remove(ride);
    }

    public boolean isValidRide(Ride ride) {
        return ride == null || !allRides.contains(ride) ? false : true;
    }
}
