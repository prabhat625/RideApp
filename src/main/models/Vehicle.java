package main.models;

import main.models.Driver;

public interface Vehicle {
    String getRegistrationNumber();
    VehicleModel getModel();
    Driver getDriver();
    default boolean registerDriver(Driver driver){
        return driver.addVehicle(this);
    }
    default boolean isTwoWheeler() {
        return false;
    }
    default boolean isFourWheeler() {
        return false;
    }
    String toString() ;
}
