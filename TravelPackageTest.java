import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TravelPackageTest {

    private TravelPackage travelPackage;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        travelPackage = new TravelPackage("Party Package", 2);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void addPassenger_Successful() {
        Passenger passenger = new Passenger("Carti", 1, PassengerType.STANDARD);
        travelPackage.addPassenger(passenger);

        assertEquals(1, travelPackage.getPassengers().size());
        assertTrue(travelPackage.getPassengers().contains(passenger));
    }

    @Test
    void addPassenger_OutOfCapacity() {
        Passenger passenger1 = new Passenger("Carti", 1, PassengerType.STANDARD);
        Passenger passenger2 = new Passenger("Arijit", 2, PassengerType.GOLD);
        Passenger passenger3 = new Passenger("Charlie", 3, PassengerType.PREMIUM);

        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);

        assertEquals(2, travelPackage.getPassengers().size());
        assertTrue(travelPackage.getPassengers().contains(passenger1));
        assertTrue(travelPackage.getPassengers().contains(passenger2));
        assertFalse(travelPackage.getPassengers().contains(passenger3));

        assertEquals("Package: Party Package is out of capacity\n", outputStreamCaptor.toString());
    }

    @Test
    void printItinerary_WithDestinationsAndActivities() {
        Destination destination1 = new Destination("Rishikesh");
        Activity activity1 = new Activity("Hiking", "Enjoy the view from summit", 50, 20);
        destination1.addActivity(activity1);
        travelPackage.addDestination(destination1);

        Destination destination2 = new Destination("Goa");
        Activity activity2 = new Activity("Snorkeling", "Explore life underwater", 100, 10);
        destination2.addActivity(activity2);
        travelPackage.addDestination(destination2);

        travelPackage.printItinerary();

        String expectedOutput = "Travel Package: Party Package\n" +
                "Destination Number 1: Rishikesh\n" +
                "Activities:\n" +
                "  Name: Hiking\n" +
                "  Description: Enjoy the view from summit\n" +
                "  Cost: 50.0\n" +
                "  Capacity: 20\n\n" +
                "Destination Number 2: Goa\n" +
                "Activities:\n" +
                "  Name: Snorkeling\n" +
                "  Description: Explore life underwater\n" +
                "  Cost: 100.0\n" +
                "  Capacity: 10\n\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void printPassengerList_NoPassengers() {
        travelPackage.printPassengerList();

        String expectedOutput = "Travel Package: Party Package\n" +
                "Passenger Capacity: 2\n" +
                "Number of Passengers Enrolled: 0\n" +
                "Passenger List:\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void printPassengerList_WithPassengers() {
        Passenger passenger1 = new Passenger("Savage", 1, PassengerType.STANDARD);
        Passenger passenger2 = new Passenger("Drake", 2, PassengerType.GOLD);
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);

        travelPackage.printPassengerList();

        String expectedOutput = "Travel Package: Party Package\n" +
                "Passenger Capacity: 2\n" +
                "Number of Passengers Enrolled: 2\n" +
                "Passenger List:\n" +
                "  Name: Savage\n" +
                "  Passenger Number: 1\n" +
                "\n" +
                "  Name: Drake\n" +
                "  Passenger Number: 2\n" +
                "\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void printPassengerDetails_PassengerNotFound() {
        travelPackage.printPassengerDetails(2);

        assertEquals("Passenger Number: 2 is unidentified.\n", outputStreamCaptor.toString());
    }

    @Test
    void printPassengerDetails_PassengerWithNoActivities() {
        Passenger passenger = new Passenger("Eminem", 1, PassengerType.STANDARD);
        travelPackage.addPassenger(passenger);
        travelPackage.printPassengerDetails(1);

        String expectedOutput = "Passenger Name: Eminem\n" +
                "Passenger Number: 1\n" +
                "No activities signed up.\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void printPassengerDetails_PassengerWithActivities() {
        Destination destination = new Destination("Rishikesh");
        Activity activity = new Activity("Hiking", "Enjoy the view from summit", 50, 20, destination);
        destination.addActivity(activity);
        Passenger passenger = new Passenger("50Cent", 1, PassengerType.STANDARD);
        passenger.setBalance(50);
        passenger.signUpForActivity(activity);
        travelPackage.addPassenger(passenger);
        travelPackage.addDestination(destination);

        travelPackage.printPassengerDetails(1);

        String expectedOutput = "Passenger Name: 50Cent\n" +
                "Passenger Number: 1\n" +
                "Activities:\n" +
                "  Activity: Hiking\n" +
                "  Destination: Rishikesh\n" +
                "  Price Paid: 50.0\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void printAvailableActivities_NoActivities() {
        travelPackage.printAvailableActivities();
        assertEquals("Available Activities:\n\n", outputStreamCaptor.toString());
    }

    @Test
    void printAvailableActivities_WithAvailableActivities() {
        Destination destination = new Destination("Rishikesh");
        Activity activity1 = new Activity("Hiking", "Enjoy the view from summit", 50, 20, destination);
        Activity activity2 = new Activity("Slithering", "Feel the free fall", 80, 15, destination);
        destination.addActivity(activity1);
        destination.addActivity(activity2);
        travelPackage.addDestination(destination);

        travelPackage.printAvailableActivities();

        String expectedOutput = "Available Activities:\n" +
                "  Activity: Hiking\n" +
                "  Destination: Rishikesh\n" +
                "  Available Spaces: 20\n" +
                "  Activity: Slithering\n" +
                "  Destination: Rishikesh\n" +
                "  Available Spaces: 15\n\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
