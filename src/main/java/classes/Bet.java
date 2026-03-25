package classes;
import utils.Constants;
/**
 * Represents a bet placed by a bettor on an event.
 * Contains information about the bettor, the bet description, and the bet amount.
 */
public class Bet {
    private String bettorName;
    private String betDescription;
    private float odds;
    private float amount;

    /**
     * Constructor that creates a new bet with the specified parameters.
     * 
     * @param bettorName the bettor's name (cannot be null or empty)
     * @param betDescription the bet description (cannot be null or empty)
     * @param odds the bet odds (must be a positive value)
     * @param amount the bet amount (must be a positive value)
     * @throws IllegalArgumentException if the bettor's name is null or empty,
     *         if the description is null or empty, or if the odds or the amount are not positive
     */
    public Bet(String bettorName, String betDescription, float odds, float amount) {
        if (bettorName == null || bettorName.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_BET_BETTOR_NAME_EMPTY);
        }
        if (betDescription == null || betDescription.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_BET_DESCRIPTION_EMPTY);
        }
        if (odds <= 0) {
            throw new IllegalArgumentException(Constants.ERROR_BET_ODDS_POSITIVE);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException(Constants.ERROR_BET_AMOUNT_POSITIVE);
        }
        this.bettorName = bettorName;
        this.betDescription = betDescription;
        this.odds = odds;
        this.amount = amount;
    }
    
    /**
     * Returns the bettor's name.
     * 
     * @return the bettor's name
     */
    public String getBettorName() {
        return bettorName;
    }
    
    /**
     * Returns the bet description.
     * 
     * @return the bet description
     */
    public String getBetDescription() {
        return betDescription;
    }

    /**
     * Returns the bet odds.
     *
     * @return the bet odds
     */
    public float getOdds() {
        return odds;
    }
    
    /**
     * Returns the bet amount.
     * 
     * @return the bet amount
     */
    public float getAmount() {
        return amount;
    }
    
    /**
     * Returns a CSV format representation of the bet.
     * The format is: bettor_name,description,odds,amount
     * 
     * @return a string in CSV format with the bet data
     */
    @Override
    public String toString() {
        return String.format(Constants.BET_TO_STRING_FORMAT, bettorName, betDescription, odds, amount);
    }
}
