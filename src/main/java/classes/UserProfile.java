package classes;

/**
 * Represents a user profile composed of a User, their preferences, and their
 * betting statistics. This class uses composition to associate additional
 * profile-related information without modifying the original User hierarchy. It
 * provides methods to access user information, preferences, and statistics, as
 * well as utilities for matching bettor IDs.
 *
 * @author IOC
 * @version 1.0
 */
public class UserProfile {

    /**
     * The user associated with this profile
     */
    private User user;
    /**
     * The user's betting preferences
     */
    private Preferences preferences;
    /**
     * The user's betting statistics
     */
    private UserStats userStats;

    /**
     * Constructs a UserProfile with the specified User. Uses default values for
     * Preferences and UserStats. Initializes new Preferences and UserStats
     * objects for this profile.
     *
     * @param user the user for this profile (must not be null)
     * @throws NullPointerException if user is null
     */
    public UserProfile(User user) {
        this.user = user;
        this.preferences = new Preferences();
        this.userStats = new UserStats();
    }

    /**
     * Constructs a UserProfile with the specified User, Preferences, and
     * UserStats.
     *
     * @param user the user for this profile (must not be null)
     * @param preferences the user's betting preferences (must not be null)
     * @param stats the user's betting statistics (must not be null)
     * @throws NullPointerException if any argument is null
     */
    public UserProfile(User user, Preferences preferences, UserStats userStats) {
        if (user == null) {
            throw new NullPointerException("L'user és null");
        }
        if (preferences == null) {
            throw new NullPointerException("L'user és null");
        }
        if (userStats == null) {
            throw new NullPointerException("L'user és null");
        }
        this.user = user;
        this.preferences = preferences;
        this.userStats = userStats;
    }

    /**
     * Retrieves the user associated with this profile.
     *
     * @return the user object
     */
    public User getUser() {

    }

    /**
     * Retrieves the user's identification number.
     *
     * @return the user's ID (DNI or NIE)
     */
    public String getUserId() {

    }

    /**
     * Retrieves the user's betting preferences.
     *
     * @return the preferences object
     */
    public Preferences getPreferences() {

    }

    /**
     * Retrieves the user's betting statistics.
     *
     * @return the statistics object
     */
    public UserStats getStats() {

    }

    /**
     * Generates a human-friendly summary of the user profile including user
     * information, total bets, and total amount. This is useful for console UI
     * display and logging.
     *
     * @return a formatted summary string containing user name, ID, email, total
     * bets, and total amount
     */
    public String getSummary() {

    }

    /**
     * Checks if the provided bettor ID matches the user's identification
     * number. Performs case-insensitive comparison by converting both IDs to
     * uppercase. This helper method avoids coupling with the original Bet/Event
     * classes by using string-only comparison.
     *
     * @param bettorId the bettor ID to compare (can be null)
     * @return true if the bettor ID matches the user's ID, false otherwise
     */
    public boolean matchesBettorId(String bettorId) {

    }

    /**
     * Returns a string representation of the UserProfile in a specific format.
     * The format includes user type, user details, preferences, and statistics.
     * For LocalPersonUser, the string starts with "LOCAL," followed by user
     * details. For ForeignPersonUser, the string starts with "FOREIGN,"
     * followed by user details and then appends preferences and statistics.
     *
     * @return a formatted string representing the UserProfile
     */
    @Override
    public String toString() {

    }
}
