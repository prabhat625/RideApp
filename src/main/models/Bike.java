package main.models;

public class Bike implements Vehicle{
    private VehicleModel model;
    private String registrationNumber;
    private Driver driver;

    public Bike(VehicleModel model, String registrationNumber, Driver driver) {
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.driver = driver;
        registerDriver(driver);
    }

    @Override
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    @Override
    public VehicleModel getModel() {
        return this.model;
    }

    @Override
    public boolean isTwoWheeler() {
        return true;
    }

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public String toString() {
        return getModel() + " | " + getRegistrationNumber() + "Driver: " + getDriver().toString();
    }
}
