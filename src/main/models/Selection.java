package main.models;

public class Selection {
    public final City source;
    public final City destination;
    public final int seatRequire;
    public final SelectionStrategy strategy;

    public Selection(City source, City destination, int seatRequire, SelectionStrategy strategy) {
        this.source  = source;
        this.destination = destination;
        this.seatRequire = seatRequire;
        this.strategy = strategy;
    }
}
