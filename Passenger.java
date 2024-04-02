import exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a passenger who signs up for activities within a travel package.
 * It contains details such as name, passenger number, passenger type, balance, and
 * a list of activities the passenger has signed up for.
 */
public class Passenger {
    private String name;
    private int passengerNumber;
    private PassengerType passengerType;
    private double balance;
    private List<Activity> activities;
    private Address address;

    public Passenger(String name, int passengerNumber, PassengerType passengerType, Address address) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.passengerType = passengerType;
        this.activities = new ArrayList<>();
        this.address = address;
    }

    public Passenger(String name, int passengerNumber, PassengerType passengerType) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.passengerType = passengerType;
        this.activities = new ArrayList<>();
        this.address = new Address();
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public double getBalance() {
        return balance;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Signs up the passenger for the activity if the passenger has sufficient balance.
     *
     * @param activity the activity to sign up for
     * @return true if the passenger is successfully signed up for the activity, false otherwise
     */
    public boolean signUpForActivity(Activity activity) {

        double discountedPrice = activity.getCost() * passengerType.getPercent();
        try {
            if (balance >= discountedPrice) {
                activity.signUp(this);
                balance -= discountedPrice;
                activities.add(activity);
                return true;
            } else {
                System.out.println("Insufficient balance in " + this.name + "'s wallet");
            }
        } catch (InvalidInputException exception) {
            System.out.println("Unable to sign up for activity!!");
        }

        return false;
    }
}
