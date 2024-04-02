import java.util.ArrayList;
import java.util.List;

/**
 * Represents a travel package offered by a travel agency.
 * It contains destinations, passengers, and has methods to add destinations,
 * add passengers, and print itinerary, passenger list, passenger details, and available activities.
 */
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

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void addPassenger(Passenger passenger) {
        if(passengers.size() >= passengerCapacity) {
            System.out.println("Package: " + name + " is out of capacity");
            return;
        }
        passengers.add(passenger);
    }

    public String getName() {
        return name;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Prints the itinerary of the travel package, including destinations and details of activities available at each destination.
     * Each destination is listed along with its associated activities, including their names, descriptions, costs, and capacities.
     */
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

    /**
     * Prints the list of passengers enrolled in the travel package, including their names and passenger numbers.
     * Also prints the total passenger capacity and the number of passengers currently enrolled.
     */
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

    /**
     * Prints the details of a specific passenger enrolled in the travel package, identified by their passenger number.
     * The details include the passenger's name, passenger number, balance (if applicable), and a list of activities
     * signed up by the passenger, including activity name, destination, and price paid for each activity.
     *
     * @param passengerNumber the passenger number of the passenger whose details are to be printed
     */
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

    /**
     * Prints the details of available activities within the travel package,
     * including the activity name, its destination, and the number of available spaces.
     */
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