package classes;



/**
 * Represents user preferences for betting behavior and notifications.
 * This class manages user settings such as minimum odds, maximum bet amount, and notification preferences.
 * Kept simple and purely didactic.
 * 
 * @author IOC
 * @version 1.0
 */
public class Preferences {

    /** Minimum odds allowed for a bet. Default value is 1.01 */

    /** Maximum amount allowed for a single bet. Default value is 500.0 */

    /** Whether notifications are enabled for the user. Default value is true */

    
    /**
     * Retrieves the minimum odds preference.
     * 
     * @return the minimum odds value
     */
    public double getMinOdd() {

    }

    /**
     * Sets the minimum odds preference.
     * 
     * @param minOdd the minimum odds value to set (must be greater than 1.0)
     * @throws IllegalArgumentException if minOdd is less than or equal to 1.0
     */
    public void setMinOdd(double minOdd) {

    }

    /**
     * Retrieves the maximum bet amount preference.
     * 
     * @return the maximum amount value
     */
    public double getMaxAmount() {

    }

    /**
     * Sets the maximum bet amount preference.
     * 
     * @param maxAmount the maximum amount value to set (must be greater than 0)
     * @throws IllegalArgumentException if maxAmount is less than or equal to 0
     */
    public void setMaxAmount(double maxAmount) {

    }

    /**
     * Checks if notifications are enabled for this user.
     * 
     * @return true if notifications are enabled, false otherwise
     */
    public boolean isNotificationsEnabled() {

    }

    /**
     * Sets the notification preference for this user.
     * 
     * @param notificationsEnabled true to enable notifications, false to disable them
     */
    public void setNotificationsEnabled(boolean notificationsEnabled) {

    }

    /**
     * Returns a string representation of the Preferences.
     * 
     * @return a string containing minOdd, maxAmount, and notificationsEnabled
     */
    @Override
    public String toString() {

    }
}
