package main.services;

import main.models.City;
import main.models.Ride;
import main.models.Selection;

import java.util.*;

public class RideUtils {
    private RideCollection rideCollection;
    RideUtils () {
        rideCollection = CollectionManager.RIDE_COLLECTION;
    }
    public Set<Ride> getDirectRides(Selection selection) {
        City source = selection.source;
        City destination = selection.destination;
        Set<Ride> availableRides = getAllRidesFromSrcToDest(source, destination, selection.seatRequire);
        return getRidesForSelection(availableRides, selection);
    }

    public Set<Ride> getRidesForSelection(Set<Ride> rides, Selection selection) {
        return selection.strategy.seatVacant != null ?
                getRidesForSeatSelection(rides, selection) : getRidesForModelSelection(rides, selection);
    }

    public Set<Ride> getRidesForSeatSelection(Set<Ride> rides, Selection selection) {
        int maxSeats = 0;
        Set<Ride> seatSelectionRides = new HashSet<>();
        for (Ride ride: rides) {
            maxSeats = Math.max(maxSeats, ride.getVacantSeats());
        }
        for (Ride ride: rides) {
            if (ride.getVacantSeats() == maxSeats) {
                seatSelectionRides.add(ride);
            }
        }
        return seatSelectionRides;
    }

    public Set<Ride> getRidesForModelSelection(Set<Ride> rides, Selection selection) {
        Set<Ride> modeSelectionRides = new HashSet<>();
        for (Ride ride: rides) {
            if (ride.getVehicle().getModel() == selection.strategy.model) {
                modeSelectionRides.add(ride);
            }
        }
        return modeSelectionRides;
    }

    public Set<Ride> getAllRidesFromSrcToDest(City source, City destination, int seatRequire) {
        Set<Ride> rides = new HashSet<>();
        if(!rideCollection.getRidesGraph().containsKey(source))
            return rides;
        for (Ride ride: rideCollection.getRidesGraph().get(source)) {
            if (ride.getDestination() == destination && ride.getVacantSeats() >= seatRequire) {
                rides.add(ride);
            }
        }
        return rides;
    }

    public Set<Ride> getIndirectRide(Selection selection) {
        return getShortestPathUsingBFS(selection.source, selection.destination, selection.seatRequire);

    }

    public Set<Ride> getShortestPathUsingBFS(City source, City destination, int requireSeats) {
        Set<Ride> path = new LinkedHashSet<>();
        Map<City, City> parent = new HashMap();
        Set<City> explore = new HashSet();
        Queue<City> queue = new LinkedList<City>(){{add(source);}};
        explore.add(source);
        parent.put(source, source); // Source city's parent is Source symbolize we start from here.
        while(queue.size() > 0) {
            City startingPoint = queue.poll();
            if (startingPoint ==  destination)
                break;
            for(Ride ride: rideCollection.getRidesBySourceCity(startingPoint)) {
                if (ride.getVacantSeats() < requireSeats || explore.contains(ride.getDestination())) continue;
                parent.put(ride.getDestination(), startingPoint);
                queue.add(ride.getDestination());
                explore.add(ride.getDestination());
            }
        }
        if (parent.containsKey(destination)) {
            // Valid path exists.
            List<City> destToSrcCites = new LinkedList();
            City currentCity = destination;
            while(parent.get(currentCity) != currentCity) {
                destToSrcCites.add(currentCity);
                currentCity = parent.get(currentCity);
            }
            destToSrcCites.add(currentCity);
            Collections.reverse(destToSrcCites); // to get a list of cites from source to dest instead of dest to source.
            for (int cityNumber = 1; cityNumber < destToSrcCites.size(); cityNumber++) {
                path.add(
                        getAllRidesFromSrcToDest(
                                destToSrcCites.get(cityNumber - 1),
                                destToSrcCites.get(cityNumber),
                                requireSeats
                        ).iterator().next() // get Any such ride.
                );
            }
        }
        return path;
    }
}
