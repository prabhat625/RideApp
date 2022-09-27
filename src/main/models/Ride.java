package main.models;

public class Ride {
    private final City source;
    private final City destination;
    private final int vacantSeats;
    private final Vehicle vehicle;
    private Passenger passenger;
    public boolean isEnded;

    public Ride(City source, City destination, int vacantSeats, Vehicle vehicle) {
        this.source = source;
        this.destination = destination;
        this.vacantSeats = vacantSeats;
        this.vehicle = vehicle;
        passenger = null;
        isEnded = false;
    }

    public City getSource() {
        return this.source;
    }

    public City getDestination() {
        return this.destination;
    }

    public int getVacantSeats() {
        return this.vacantSeats;
    }
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public boolean isActiveRide() {
        return !isEnded && this.passenger != null;
    }

    public void endRide() {
        isEnded = true;
    }

    @Override
    public String toString() {
        return this.source + " to " + this.destination + " with " + this.vacantSeats + " vacant seat available on " + this.vehicle.toString();
    }
}
