/**
 * Enum representing the type of passenger.
 * Each passenger type has a corresponding discount percentage for activities.
 * - STANDARD: Standard passenger with no discount.
 * - GOLD: Gold passenger with a 10% discount.
 * - PREMIUM: Premium passenger with a 100% discount.
 */
public enum PassengerType {

    STANDARD(1.0),
    GOLD(0.9),
    PREMIUM(0);

    private double percent;

    /**
     * Constructs a PassengerType with the specified discount percentage.
     * @param percent The discount percentage for the passenger type.
     */
    PassengerType(double percent) {
        this.percent = percent;
    }

    /**
     * Returns the discount percentage for the passenger type.
     * @return The discount percentage.
     */
    public double getPercent() {
        return percent;
    }
}
