public class TravelAgency {
    public static void main(String[] args) {
        // Create activities and destinations
        Activity activity1 = new Activity("Hiking", "Enjoy the view from summit", 50, 20);
        Activity activity2 = new Activity("Snorkeling", "Explore life underwater", 80, 15);
        Activity activity3 = new Activity("Slithering", "Feel the free fall", 100, 5);

        Destination destination1 = new Destination("Rishikesh");
        destination1.addActivity(activity1);
        destination1.addActivity(activity3);
        activity1.setDestination(destination1);
        activity3.setDestination(destination1);

        Destination destination2 = new Destination("Goa");
        destination2.addActivity(activity2);
        activity2.setDestination(destination2);

        // Create passengers
        Passenger passenger1 = new Passenger("Savage", 1, PassengerType.STANDARD, new Address());
        Passenger passenger2 = new Passenger("Drake", 2, PassengerType.GOLD, new Address());
        Passenger passenger3 = new Passenger("Travis", 3, PassengerType.PREMIUM, new Address());

        // Top-up balance
        passenger1.setBalance(80d);
        passenger2.setBalance(200d);

        // Create travel package
        TravelPackage travelPackage = new TravelPackage("Party Package", 10);
        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);

        // Sign up passengers for activities
        passenger1.signUpForActivity(activity1);
        passenger2.signUpForActivity(activity2);
        passenger2.signUpForActivity(activity3);
        passenger3.signUpForActivity(activity1);

        // Test functionality
        travelPackage.printItinerary();
        System.out.println();

        travelPackage.printPassengerList();
        System.out.println();

        travelPackage.printPassengerDetails(2);
        System.out.println();

        travelPackage.printAvailableActivities();
    }
}
