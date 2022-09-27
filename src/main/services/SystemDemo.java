package main.services;

import main.models.*;

import java.util.Set;

public class SystemDemo {
    public static void main(String[] args) {

        RideManagement system = new RideManagement();

        // Driver
        Driver d1 = new Driver("Shashank", "kumar", 123432);
        Driver d2 = new Driver("Rohan", "kumar", 121132);
        Driver d3 = new Driver("Shipra", "kumari", 121132);
        Driver d4 = new Driver("Rahul", "kumar", 121132);


        // Vehicle
        Vehicle v1 = new Car(VehicleModel.SWIFT, "KA-01-12345", d2);
        Vehicle v2 = new Car(VehicleModel.BALENO, "TS-05-62395", d1);
        Vehicle v3 = new Car(VehicleModel.POLO, "KA-05-41491", d3);
        Vehicle v4 = new Car(VehicleModel.XUV, "KA-05-1234", d4);
        Vehicle v5 = new Bike(VehicleModel.ACTIVA, "KA-12-12332", d3);

        // Passenger
        Passenger p1 = new Passenger("Shashank", "Kumar", 983212);
        Passenger p2 = new Passenger("Rohan", "Kumar", 983212);
        Passenger p3 = new Passenger("Nandini", "Kumari", 983212);
        Passenger p4 = new Passenger("Shipra", "Kumar1", 983212);
        Passenger p5 = new Passenger("Gaurav", "Kumar", 983212);
        Passenger p6 = new Passenger("rahul", "Kumar", 983212);

        // Ride
        Ride r1 = new Ride(City.HYDERABAD, City.BANGALORE, 1,v1);
        Ride r2 = new Ride(City.BANGALORE, City.MYSORE, 1,v5);
        Ride r3 = new Ride(City.BANGALORE, City.MYSORE, 2,v3);
        Ride r4 = new Ride(City.HYDERABAD, City.BANGALORE, 2,v2);
        Ride r5 = new Ride(City.HYDERABAD, City.BANGALORE, 5,v4);
        system.offerRide(r1);
        system.offerRide(r2);
        system.offerRide(r3);
        system.offerRide(r4);
        system.offerRide(r5);

        // Selection
        Selection s1 = new Selection(City.BANGALORE,City.MYSORE, 1,new SelectionStrategy(VacantSeats.MOST_VACANT));
        Selection s2 = new Selection(City.BANGALORE,City.MYSORE, 1,new SelectionStrategy(VehicleModel.ACTIVA));
        Selection s3 = new Selection(City.MUMBAI,City.BANGALORE, 1,new SelectionStrategy(VacantSeats.MOST_VACANT));
        Selection s4 = new Selection(City.HYDERABAD,City.BANGALORE, 1,new SelectionStrategy(VehicleModel.BALENO));
        // For below 2 cases: Instead of no ride we return a possible ride  since there is no direct ride that match exact selection
        Selection s5 = new Selection(City.HYDERABAD,City.BANGALORE, 1,new SelectionStrategy(VehicleModel.POLO));
        Selection s6 = new Selection(City.HYDERABAD,City.MYSORE, 1,new SelectionStrategy(VehicleModel.POLO));

        printRides(system.selectRide(p3,s1));
        printRides(system.selectRide(p5,s2));
        printRides(system.selectRide(p1,s3));
        printRides(system.selectRide(p2,s4));
        printRides(system.selectRide(p1,s5));
        printRides(system.selectRide(p1,s6));

        System.out.println(system.getRideStats());
    }
    public static void printRides(Set<Ride> rides) {
        if(rides.size() == 0)
            System.out.println("No rides found");
        for (Ride ride: rides) {
            System.out.println(ride.toString());
        }
        System.out.println("********************");
    }
}
