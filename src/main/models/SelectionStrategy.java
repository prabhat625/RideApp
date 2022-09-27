package main.models;

public class SelectionStrategy {
    public final VehicleModel model;
    public final VacantSeats seatVacant;

    public SelectionStrategy() {
        this.seatVacant = VacantSeats.MOST_VACANT;
        this.model = null;
    }
    public SelectionStrategy(VehicleModel model) {
        this.model = model;
        this.seatVacant = null;
    }

    public SelectionStrategy(VacantSeats seatVacant) {
        this.seatVacant = seatVacant;
        this.model = null;
    }
}
