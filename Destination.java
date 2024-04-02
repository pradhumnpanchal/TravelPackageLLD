import java.util.ArrayList;
import java.util.List;

/**
 * Represents a destination within a travel package.
 * It contains activities available at that destination and has a method to add activities.
 */
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

    public String getName() {
        return name;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
