package main.services;

import main.models.Driver;

import java.util.Set;
import java.util.HashSet;

public class DriverCollection {
    Set<Driver> driverSet;
    private static DriverCollection instance ;

    private DriverCollection() {
        driverSet = new HashSet<Driver>();
    }
    public void addDriver(Driver driver) {
        driverSet.add(driver);
    }
    public static DriverCollection getInstance() {
        if (instance == null) {
            instance = new DriverCollection();
        }
        return instance;
    }

    public boolean isValidDriver(Driver driver) {
        return driver == null || !driverSet.contains(driver) ? false : true;
    }
}
