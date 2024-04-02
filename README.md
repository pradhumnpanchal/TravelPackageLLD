# Design a Travel Package Management System

A Travel Package management system allows travel agencies to maintain their travel packages, itineraries and passengers. All operations of a travel package are controlled by Travel Package management system.

This system involves defining a travel package including destinations and activities with capacity and pricing. 

## System Requirements

We will focus on the following set of requirements while designing the Travel Package Management System:

1. User can check the itinerary details comprising destinations and details of the activities available at each destination, like name, cost, capacity and description.
2. User can check the passenger list of the travel package including package name, passenger capacity, number of passengers currently enrolled and name and number of each passenger. 
3. User can fetch the details for a passenger including their name, passenger number, balance (if applicable), list of activity they have signed up for, including the destination at which the activity is taking place and the price the passenger paid for the activity.
4. User can fetch the activities with the available slots. 

## Class Diagram

Here are the main classes of our Travel Package Management System:

* **TravelPackage**: Represents a travel package offered by a travel agency. It contains destinations, passengers, and has methods to add destinations, add passengers, and print itinerary, passenger list, passenger details, and available activities.
* **Passenger**: Represents a passenger who signs up for activities within a travel package. It contains details such as name, passenger number, passenger type, balance, and a list of activities the passenger has signed up for. It also has a method to sign up for activities.
* **Destination**: Represents a destination within a travel package. It contains activities available at that destination and has a method to add activities.
* **Activity**: Represents an activity available at a destination. It contains details such as name, description, cost, and capacity. It also keeps track of the passengers signed up for the activity.
* **Address**: Represents the address of a passenger.
* **PassengerType**: Enumerates the types of passengers (Standard, Gold, Premium) with associated discounts.

![UML](https://github.com/pradhumnpanchal/TravelPackageLLD/blob/main/Main.png)

## Code
Here is the code for classes, Enum including unit tests. For complete class implementation and unit tests for them refer the java files.

- Here is the required Enum:
```
public enum PassengerType {

    STANDARD(1.0),
    GOLD(0.9),
    PREMIUM(0);

    private double percent;
    PassengerType(double v) {
        this.percent = v;
    }

    public double getPercent() {
        return percent;
    }
}
```

- Here is the exception class:
```
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }

}
```

Here the different classes being implemented:

**Activity**
```
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

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public boolean signUp(Passenger passenger) {

        if (passengers.size() < capacity) {
            passengers.add(passenger);
            return true;
        } else {
            throw new InvalidInputException("Capacity out of bound");
        }
    }
}
```

**Destination**
```
public class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
```

**Passenger**
```
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
```

**TravelPackage**
```
public class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> destinations;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.destinations = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) {
        if(passengers.size() >= passengerCapacity) {
            System.out.println("Package: " + name + " is out of capacity");
            return;
        }
        passengers.add(passenger);
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        int destinationCount = 1;
        for (Destination destination : destinations) {
            System.out.println("Destination Number " + destinationCount++ + ": " + destination.getName());
            System.out.println("Activities:");
            for (Activity activity : destination.getActivities()) {
                System.out.println("  Name: " + activity.getName());
                System.out.println("  Description: " + activity.getDescription());
                System.out.println("  Cost: " + activity.getCost());
                System.out.println("  Capacity: " + activity.getCapacity());
                System.out.println();
            }
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers Enrolled: " + passengers.size());
        System.out.println("Passenger List:");
        for (Passenger passenger : passengers) {
            System.out.println("  Name: " + passenger.getName());
            System.out.println("  Passenger Number: " + passenger.getPassengerNumber());
            System.out.println();
        }
    }

    public void printPassengerDetails(int passengerNumber) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerNumber() == passengerNumber) {
                System.out.println("Passenger Name: " + passenger.getName());
                System.out.println("Passenger Number: " + passenger.getPassengerNumber());
                if (passenger.getBalance() > 0) {
                    System.out.println("Balance: " + passenger.getBalance());
                }
                if (!passenger.getActivities().isEmpty()) {
                    System.out.println("Activities:");
                    for (Activity activity : passenger.getActivities()) {
                        System.out.println("  Activity: " + activity.getName());
                        System.out.println("  Destination: " + activity.getDestination().getName());

                        double pricePaid = activity.getCost() * passenger.getPassengerType().getPercent();
                        System.out.println("  Price Paid: " + pricePaid);
                    }
                } else {
                    System.out.println("No activities signed up.");
                }
                return;
            }
        }
        System.out.println("Passenger Number: " + passengerNumber + " is unidentified.");
    }

    public void printAvailableActivities() {
        System.out.println("Available Activities:");
        for (Destination destination : destinations) {
            for (Activity activity : destination.getActivities()) {
                int availableSpaces = activity.getCapacity() - activity.getPassengers().size();
                if (availableSpaces > 0) {
                    System.out.println("  Activity: " + activity.getName());
                    System.out.println("  Destination: " + destination.getName());
                    System.out.println("  Available Spaces: " + availableSpaces);
                }
            }
        }
        System.out.println();
    }
}
```
