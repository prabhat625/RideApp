package main.services;

import main.models.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RideManagement {
    private RideCollection rideCollection;
    private RideUtils rideUtils;

    RideManagement() {
        rideCollection = CollectionManager.RIDE_COLLECTION;
        rideUtils = new RideUtils();
    }

    public boolean offerRide(Ride ride) {
        return rideCollection.addNewOfferRide(ride);
    }

    public Set<Ride> selectRide(Passenger passenger, Selection selection) {
        Set<Ride> directRides = rideUtils.getDirectRides(selection);
        Set<Ride> indirectRides = null;
        if (directRides.size() == 0) {
            indirectRides = rideUtils.getIndirectRide(selection);
        }
        return  (directRides.size() > 0) ?
                new HashSet<>() {{add(directRides.iterator().next());}} // Only one ride is required.
                : indirectRides;
    }

    public boolean endRide(Ride ride) {
        ride.endRide();
        return rideCollection.activeRideEnd(ride);
    }

    public String getRideStats(){
        StringBuilder str = new StringBuilder();
        // Get Driver ride stats
        Iterator<Driver> ditr = rideCollection.getDriverToRidesMap().keySet().iterator();
        while(ditr.hasNext()) {
            Driver driver = ditr.next();
            Set<Ride> ride = rideCollection.getDriverToRidesMap().get(driver);
            str.append("Total ride offered by "+ driver + " : "+ ride.size() + "\n");
        }

        // Get Passenger ride stats
        Iterator<Passenger> pitr = rideCollection.getPassengerToRidesMap().keySet().iterator();
        while(pitr.hasNext()) {
            Passenger passenger = pitr.next();
            Set<Ride> rides = rideCollection.getPassengerToRidesMap().get(passenger);
            str.append("Total ride taken by "+ passenger + " : "+ rides.size() + "\n");
        }
        return str.toString();
    }


}
