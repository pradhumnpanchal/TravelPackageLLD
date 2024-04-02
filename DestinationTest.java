import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DestinationTest {

    private Destination destination;

    @BeforeEach
    void setUp() {
        destination = new Destination("Rishikesh");
    }

    @Test
    void addActivity() {
        Activity activity = new Activity("Hiking", "Enjoy the view from summit", 50, 20);
        destination.addActivity(activity);

        assertEquals(1, destination.getActivities().size());
        assertTrue(destination.getActivities().contains(activity));
    }
}
