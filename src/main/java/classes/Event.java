package classes;
import utils.Constants;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a sporting event on which bets can be placed.
 * Each event has a type, a name, a timestamp,
 * and a set of associated bets.
 */
public class Event {
    private String type;
    private String name;
    private LocalDateTime timestamp;
    private Map<String, Bet> bets;

    /**
     * Constructor that creates a new event with the specified parameters.
     * 
     * @param type the event type (cannot be null or empty)
     * @param name the event name (cannot be null or empty)
     * @param timestamp the event timestamp (cannot be null and must be in the future)
     * @throws IllegalArgumentException if the type or name are null or empty,
     *         if the timestamp is null, or if the timestamp is not in the future
     */
    public Event(String type, String name, LocalDateTime timestamp) {
        // check there is no null or empty strings. Throw IllegalArgumentException if so
        if (type == null || type.isEmpty() ||
            name == null || name.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_TYPE_NAME_EMPTY);
            }
        // check timestamp is not null. Throw IllegalArgumentException if so
        if (timestamp == null) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_TIMESTAMP_NULL);
            }
        // check timestamp is in the future. Throw IllegalArgumentException if not so
        if (!timestamp.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_TIMESTAMP_FUTURE);
            }
        this.type = type;
        this.name = name;
        this.timestamp = timestamp;
        bets = new HashMap<>();
    }

    /**
     * Returns the event type.
     * 
     * @return the event type
     */
    public String getType() {
        return type;
    }    

    /**
     * Returns the event name.
     * 
     * @return the event name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the event timestamp.
     * 
     * @return the event timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the event type.
     * 
     * @param type the new event type (cannot be null or empty)
     * @throws IllegalArgumentException if the type is null or empty
     */
    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_TYPE_NAME_EMPTY);
        }   
        this.type = type;
    }

    /**
     * Sets the event name.
     * 
     * @param name the new event name (cannot be null or empty)
     * @throws IllegalArgumentException if the name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_TYPE_NAME_EMPTY);
        }
        this.name = name;
    }

    /**
     * Sets the event timestamp.
     * 
     * @param timestamp the new timestamp (cannot be null and must be in the future)
     * @throws IllegalArgumentException if the timestamp is null or not in the future
     */
    public void setTimestamp(LocalDateTime timestamp) {
        if (timestamp == null) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_TIMESTAMP_NULL);
        }
        if (!timestamp.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_TIMESTAMP_FUTURE);
        }
        this.timestamp = timestamp;
    }   

    /**
     * Adds a bet to the event.
     * If the bettor already exists, an error is thrown and the existing bet remains unchanged.
     * 
     * @param betDescription the bet description (cannot be null or empty)
     * @param bettor the bettor name (cannot be null or empty)
     * @param odds the bet odds (must be a positive value)
     * @param amount the bet amount (must be a positive value)
     * @throws IllegalArgumentException if the description or bettor are null or empty,
     *         if the bettor already exists, or if the odds or the amount are not positive
     */
    public void addBet(String betDescription, String bettor, float odds, float amount) {
        if (bettor == null || bettor.isEmpty() || betDescription == null || betDescription.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_BETTOR_OR_DESCRIPTION_EMPTY);
        }
        if (odds <= 0) {
            throw new IllegalArgumentException(Constants.ERROR_BET_ODDS_POSITIVE);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_BET_AMOUNT_POSITIVE);
        }
        if (bets.containsKey(bettor)) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_BETTOR_ALREADY_EXISTS);
        }
        Bet newBet = new Bet(bettor, betDescription, odds, amount);
        bets.put(bettor, newBet);
    }

    /**
     * Returns the bet associated with a bettor.
     * 
     * @param bettor the bettor name (cannot be null or empty)
     * @return the Bet object associated with the bettor
     * @throws IllegalArgumentException if the bettor is null or empty,
     *         or if the bettor is not found in the event
     */
    public Bet getBet(String bettor) {
        if (bettor == null || bettor.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_BETTOR_OR_DESCRIPTION_EMPTY);
        }
        // if bettor not found, throw IllegalArgumentException
        if (!bets.containsKey(bettor)) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_BETTOR_NOT_FOUND);
        }   
        return bets.get(bettor);
    }

    /**
     * Checks if a bettor exists in this event.
     * 
     * @param bettor the bettor name to check (cannot be null or empty)
     * @return true if the bettor exists, false otherwise
     * @throws IllegalArgumentException if the bettor is null or empty
     */
    public boolean bettorExists(String bettor) {
        if (bettor == null || bettor.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_EVENT_BETTOR_OR_DESCRIPTION_EMPTY);
        }
        return bets.containsKey(bettor);
    }   

    /**
     * Returns a defensive copy of all bets associated to this event, keyed by bettor name.
     *
     * @return a new map containing all bets keyed by bettor
     * 
     */
    public Map<String, Bet> getAllBets() {
        return new HashMap<>(bets);
    }

    /**
     * Returns a textual representation of all bets in the event.
     * Each bet is separated by a newline.
     *
     * @return a string with all formatted bets, or an empty string if there are no bets
     */
    public String betsToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Bet> entry : bets.entrySet()) {
            sb.append(entry.getValue().toString())
              .append(Constants.NEWLINE);
        }
        return sb.toString();
    }

    /**
     * Returns a CSV format representation of the event.
     * The format is: type,name,timestamp
     * 
     * @return a CSV format string with the event data
     */
    public String eventToString() {

        return String.format(Constants.EVENT_TO_STRING_FORMAT, type, name, timestamp.toString());
    }

    /**
     * Returns a CSV format representation of all the events, plus bet information.
     * 
     * @return a CSV format string with the event + bet data
     */
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Bet> entry : bets.entrySet()) {
            sb.append(this.eventToString())
              .append(Constants.SEPARATOR)
              .append(entry.getValue().toString())
              .append(Constants.NEWLINE);
        }
        // remove the last newline if there are bets
        if (!bets.isEmpty()) {
            sb.setLength(sb.length() - Constants.NEWLINE.length());
        }
        return sb.toString();
    }
}