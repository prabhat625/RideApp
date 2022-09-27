package main.models;

public class Passenger implements User{
    private String firstName;
    private String lastName;
    private int contactNumber;

    public Passenger(String first, String last, int mobile) {
        this.firstName = first;
        this.lastName = last;
        this.contactNumber = mobile;
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
}
