package classes;
import utils.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of events.
 * Maintains a collection of unique events where each event name must be unique.
 * Provides functionality to add, retrieve, and display events.
 */
public class EventList {
    private List<Event> events;

    /**
     * Constructor that creates a new empty event list.
     */
    public EventList() {
        events = new ArrayList<>();
    }

    /**
     * Adds a new event to the list.
     * The event name must be unique within the list.
     * 
     * @param event the event to add (cannot be null)
     * @throws IllegalArgumentException if the event is null or if an event with the same name already exists
     */
    public void addEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException(Constants.ERROR_EVENTLIST_EVENT_NULL);
        }
        // the Event name is unique in the list. Look for it before adding
        for (Event e : events) {
            if (e.getName().equals(event.getName())) {
                throw new IllegalArgumentException(Constants.ERROR_EVENTLIST_DUPLICATE_NAME);
            }
        }
        events.add(event);
    }

    /**
     * Returns a copy of the list of events.
     * The returned list is a new list that contains the same event references,
     * protecting the internal list from external modifications.
     * 
     * @return a new list containing all the events
     */
    public List<Event> getEvents() {
        List<Event> newList = new ArrayList<>(events);
        return newList;    
    }

    /**
     * Retrieves an event by its name.
     * 
     * @param name the name of the event to search for
     * @return the event with the specified name
     * @throws IllegalArgumentException if no event with the given name exists
     */
    public Event getEventByName(String name) {
        for (Event e : events) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        throw new IllegalArgumentException(Constants.ERROR_EVENTLIST_EVENT_NOT_FOUND);
    }

    /**
     * Returns a string representation of all events in the list.
     * Each event and its associated bets are represented using their toString() methods.
     * 
     * @return a string with all events and their bets, one per line
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Event e : events) {
            sb.append(e.toString()).append(Constants.NEWLINE);
        }
        return sb.toString();
    }

    /**
     * Returns a formatted representation of all events and their bets for board display.
     * Each event is formatted with its associated bets, with special separators.
     * 
     * @return a formatted string with all events and their bets for board display
     */
    public String createEventsToBoard() {
        StringBuilder sb = new StringBuilder();
        for (Event e : events) {
            sb.append(e.eventToString()).
            append(Constants.SEPARATOR).append(Constants.SPACE).append(Constants.SEPARATOR).append(Constants.SPACE).append(Constants.SEPARATOR).append(Constants.SPACE).append(Constants.SEPARATOR).append(Constants.SPACE).append(Constants.NEWLINE);
            for (Bet b : e.getAllBets().values()) {
                sb.append(Constants.SEPARATOR).append(Constants.SPACE).append(Constants.SEPARATOR).append(Constants.SPACE).append(Constants.SEPARATOR).append(Constants.SPACE).append(b.toString()).append(Constants.NEWLINE);
            }
        }
        return sb.toString();
    }

}