package classes;

/**
 * Tracks and manages betting statistics for a user. This class maintains the
 * total number of bets placed and the total amount wagered. It provides methods
 * to retrieve statistics and register new bets. Kept minimal and purely
 * didactic for educational purposes.
 *
 * @author IOC
 * @version 1.0
 */
public class UserStats {

    /**
     * Total number of bets placed by the user
     */
    private int totalBets;
    /**
     * Total amount wagered by the user in all bets
     */
    private double totalAmount;

    /**
     * Polimorfic constructor First of all, the version without parameters.
     * Initialize the two properties to its default values. totalBets to 0 and
     * totalAmount to 0.0
     *
     */
    public UserStats() {
        this.totalBets = 0;
        this.totalAmount = 0.0;
    }

    /**
     * Constructor with parameters
     *
     * @param totalBets the initial total number of bets (must be non-negative)
     * @param totalAmount the initial total amount wagered (must be
     * non-negative)
     * @throws IllegalArgumentException if totalBets or totalAmount are negative
     */
    public UserStats(int totalBets, double totalAmount) {
        if(totalBets < 0){
            throw new IllegalArgumentException("")
        }
        this.totalBets = totalBets;
        this.totalAmount = totalAmount;
    }

    /**
     * Retrieves the total number of bets placed by this user.
     *
     * @return the total number of bets
     */
    public int getTotalBets() {

    }

    /**
     * Retrieves the total amount wagered by this user across all bets.
     *
     * @return the total amount wagered
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Calculates the average amount per bet. Returns 0.0 if no bets have been
     * placed to avoid division by zero.
     *
     * @return the average bet amount, or 0.0 if totalBets is 0
     */
    public double getAverageAmount() {

    }

    /**
     * Registers a new bet and updates the total statistics. Increments the
     * total bet count and adds the amount to the total wagered.
     *
     * @param amount the amount to be wagered (must be greater than 0)
     * @throws IllegalArgumentException if amount is less than or equal to 0
     */
    public void registerBet(double amount) {

    }

    /**
     * Returns a string representation of the UserStats.
     *
     * @return a string containing totalBets and totalAmount
     */
    @Override
    public String toString() {

    }

}
