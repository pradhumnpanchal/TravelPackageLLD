import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerTest {

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger("Travis", 1, PassengerType.STANDARD);
    }

    @Test
    void signUpForActivity_Successful() {
        Activity activity = new Activity("Hiking", "Enjoy the view from summit", 50, 2);
        passenger.setBalance(100);

        assertTrue(passenger.signUpForActivity(activity));
        assertEquals(1, passenger.getActivities().size());
    }

    @Test
    void signUpForActivity_InsufficientBalance() {
        Activity activity = new Activity("Hiking", "Enjoy the view from summit", 50, 2);
        passenger.setBalance(20);

        assertFalse(passenger.signUpForActivity(activity));
        assertEquals(0, passenger.getActivities().size());
    }

    @Test
    void signUpForActivity_DiscountedPrice() {
        Activity activity = new Activity("Hiking", "Enjoy the view from summit", 100, 2);
        passenger.setBalance(200);
        Passenger passenger2 = new Passenger("Drake", 2, PassengerType.PREMIUM);
        passenger2.setBalance(50);

        assertTrue(passenger.signUpForActivity(activity));
        assertTrue(passenger2.signUpForActivity(activity));
        assertEquals(1, passenger.getActivities().size());
        assertEquals(100, passenger.getBalance());
        assertEquals(50, passenger2.getBalance());
    }

    @Test
    void signUpForActivity_ActivityFull() {
        Activity activity = new Activity("Hiking", "Enjoy the view from summit", 50, 1);
        Passenger passenger2 = new Passenger("Drake", 2, PassengerType.STANDARD);
        passenger.setBalance(100);

        assertTrue(passenger.signUpForActivity(activity));
        assertFalse(passenger2.signUpForActivity(activity));
        assertEquals(1, passenger.getActivities().size());
        assertEquals(0, passenger2.getActivities().size());
    }
}
