package main.models;

public interface User {
    public String getFirstName();
    public String getLastName();
    public default String getFullName() {
        return getFirstName() + getLastName();
    }
    public int getContactNumber();
}
