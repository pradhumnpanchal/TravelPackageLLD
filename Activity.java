import exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an activity available at a destination.
 * It contains details such as name, description, cost, and capacity.
 * It also keeps track of the passengers signed up for the activity.
 */
public class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private List<Passenger> passengers;
    private Destination destination;

    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.passengers = new ArrayList<>();
        this.destination = destination;
    }

    public Activity(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.passengers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    /**
     * Signs up a passenger for this activity if there is available capacity.
     *
     * @param passenger the passenger to sign up for this activity
     * @return true if the passenger is successfully signed up, false otherwise
     * @throws InvalidInputException if the activity is already at maximum capacity
     */
    public boolean signUp(Passenger passenger) {

        if (passengers.size() < capacity) {
            passengers.add(passenger);
            return true;
        } else {
            throw new InvalidInputException("Capacity out of bound");
        }
    }
}