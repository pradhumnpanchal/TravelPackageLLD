import exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActivityTest {

    private Activity activity;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        activity = new Activity("Hiking", "Enjoy the view from summit", 50, 2);
        passenger = new Passenger("Savage", 1, PassengerType.STANDARD);
    }

    @Test
    void signUp_Successful() {
        assertTrue(activity.signUp(passenger));
        assertEquals(1, activity.getPassengers().size());
    }

    @Test
    void signUp_FullCapacity() {
        Passenger passenger2 = new Passenger("Drake", 2, PassengerType.STANDARD);
        Passenger passenger3 = new Passenger("Travis", 3, PassengerType.STANDARD);

        assertTrue(activity.signUp(passenger));
        assertTrue(activity.signUp(passenger2));
        assertThrows(InvalidInputException.class, () -> activity.signUp(passenger3));
        assertEquals(2, activity.getPassengers().size());
    }
}
