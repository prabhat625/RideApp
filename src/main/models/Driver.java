package main.models;

import java.util.HashSet;
import java.util.Set;

public class Driver implements User{
    private String firstName;
    private String lastName;
    private int contactNumber;
    private Set<Vehicle> vehicles;

    public Driver(String first, String last, int mobile) {
        this.firstName = first;
        this.lastName = last;
        this.contactNumber = mobile;
        vehicles = new HashSet<Vehicle>();
    }
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public int getContactNumber() {
        return this.contactNumber;
    }

    @Override
    public String toString() {
        return this.getFullName() + " | " + this.contactNumber;
    }

    public Set<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
            return true;
        }
        return false;
    }
}
