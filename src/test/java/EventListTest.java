import classes.EventList;
import classes.Event;
import classes.Bet;
import utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EventList Class Tests")
public class EventListTest {
    
    // Display name constants
    private static final String TEST_CONSTRUCTOR = "Constructor Tests";
    private static final String TEST_CREATE_EMPTY_LIST = "Should create an empty event list";
    private static final String TEST_ADD_EVENT = "AddEvent Tests";
    private static final String TEST_ADD_VALID_EVENT = "Should add a valid event to the list";
    private static final String TEST_ADD_MULTIPLE_EVENTS = "Should add multiple events to the list";
    private static final String TEST_ADD_NULL_EVENT = "Should throw exception when adding null event";
    private static final String TEST_ADD_DUPLICATE_NAME = "Should throw exception when adding event with duplicate name";
    private static final String TEST_MAINTAIN_ONE_AFTER_DUPLICATE = "Should maintain only one event when duplicate name is attempted";
    private static final String TEST_GET_EVENTS = "GetEvents Tests";
    private static final String TEST_GET_EVENTS_EMPTY = "Should return empty list when no events added";
    private static final String TEST_GET_EVENTS_WITH_DATA = "Should return list with all added events";
    private static final String TEST_GET_EVENTS_DEFENSIVE_COPY = "Should return a defensive copy of the list";
    private static final String TEST_GET_EVENTS_ADD_NO_AFFECT = "Should allow adding to returned list without affecting internal list";
    private static final String TEST_GET_EVENT_BY_NAME = "GetEventByName Tests";
    private static final String TEST_GET_EVENT_BY_NAME_FOUND = "Should return event when name exists";
    private static final String TEST_GET_EVENT_BY_NAME_MULTIPLE = "Should return correct event among multiple events";
    private static final String TEST_GET_EVENT_BY_NAME_NOT_FOUND = "Should throw exception when event name not found";
    private static final String TEST_GET_EVENT_BY_NAME_EMPTY_LIST = "Should throw exception when searching in empty list";
    private static final String TEST_GET_EVENT_BY_NAME_CASE_SENSITIVE = "Should perform case-sensitive name search";
    private static final String TEST_TO_STRING = "ToString Tests";
    private static final String TEST_TO_STRING_EMPTY = "Should return empty string for empty list";
    private static final String TEST_TO_STRING_NO_BETS = "Should return blank content when events have no bets";
    private static final String TEST_TO_STRING_SINGLE_WITH_BET = "Should format single event with bet data";
    private static final String TEST_TO_STRING_MULTIPLE_WITH_BETS = "Should format multiple events with bet data";
    private static final String TEST_TO_STRING_DELEGATES = "Should append Event.toString output for each event";
    private static final String TEST_INTEGRATION = "Integration Tests";
    private static final String TEST_COMPLETE_WORKFLOW = "Should handle complete workflow: add, retrieve, and display events";
    private static final String TEST_DIFFERENT_TYPE_SAME_NAME = "Should handle adding events with different types but same names correctly";
    private static final String TEST_EVENT_INTEGRITY = "Should maintain event integrity after multiple operations";
    private static final String TEST_CREATE_EVENTS_TO_BOARD = "CreateEventsToBoard Tests";
    private static final String TEST_BOARD_SINGLE_NO_BETS = "Should include event header even without bets";
    private static final String TEST_BOARD_SINGLE_WITH_BETS = "Should include event header and bet rows";
    private static final String TEST_BOARD_MULTIPLE_WITH_BETS = "Should include multiple event blocks";
    
    // Test data constants
    private static final String TYPE_SOCCER = "Soccer";
    private static final String TYPE_BASKETBALL = "Basketball";
    private static final String TYPE_TENNIS = "Tennis";
    private static final String TYPE_DIFFERENT = "Different Type";
    private static final String EVENT_REAL_MADRID_BARCELONA = "Real Madrid - Barcelona";
    private static final String EVENT_LAKERS_WARRIORS = "Lakers - Warriors";
    private static final String EVENT_NADAL_FEDERER = "Nadal - Federer";
    private static final String EVENT_NONEXISTENT = "Nonexistent Event";
    private static final String EVENT_ANY = "Any Event";
    private static final String EVENT_REAL_MADRID_LOWERCASE = "real madrid - barcelona";
    private static final String EMPTY_STRING = "";
    
    private EventList eventList;
    private Event event1;
    private Event event2;
    private Event event3;
    private LocalDateTime futureTime;
    
    @BeforeEach
    void setUp() {
        eventList = new EventList();
        futureTime = LocalDateTime.now().plusDays(1);
        event1 = new Event(TYPE_SOCCER, EVENT_REAL_MADRID_BARCELONA, futureTime);
        event2 = new Event(TYPE_BASKETBALL, EVENT_LAKERS_WARRIORS, futureTime.plusHours(2));
        event3 = new Event(TYPE_TENNIS, EVENT_NADAL_FEDERER, futureTime.plusHours(3));
    }

    @Nested
    @DisplayName(TEST_CREATE_EVENTS_TO_BOARD)
    class CreateEventsToBoardTests {

        @Test
        @DisplayName(TEST_BOARD_SINGLE_NO_BETS)
        void testCreateEventsToBoardSingleEventNoBets() {
            eventList.addEvent(event1);

            String expected = new StringBuilder()
                    .append(event1.eventToString())
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.NEWLINE)
                    .toString();

            assertEquals(expected, eventList.createEventsToBoard());
        }

        @Test
        @DisplayName(TEST_BOARD_SINGLE_WITH_BETS)
        void testCreateEventsToBoardSingleEventWithBets() {
            event1.addBet("Win Bet", "Bettor1", 2.0f, 50.0f);
            eventList.addEvent(event1);
            Bet bet = event1.getBet("Bettor1");

            StringBuilder expected = new StringBuilder();
            expected.append(event1.eventToString())
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.NEWLINE);
            expected.append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(bet.toString())
                    .append(Constants.NEWLINE);

            assertEquals(expected.toString(), eventList.createEventsToBoard());
        }

        @Test
        @DisplayName(TEST_BOARD_MULTIPLE_WITH_BETS)
        void testCreateEventsToBoardMultipleEventsWithBets() {
            event1.addBet("Win Bet", "Bettor1", 2.0f, 50.0f);
            event2.addBet("Score Bet", "Bettor2", 3.0f, 25.0f);
            eventList.addEvent(event1);
            eventList.addEvent(event2);

            StringBuilder expected = new StringBuilder();
            expected.append(event1.eventToString())
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.NEWLINE);
            expected.append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(event1.getBet("Bettor1").toString())
                    .append(Constants.NEWLINE);
            expected.append(event2.eventToString())
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.NEWLINE);
            expected.append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(Constants.SEPARATOR).append(Constants.SPACE)
                    .append(event2.getBet("Bettor2").toString())
                    .append(Constants.NEWLINE);

            assertEquals(expected.toString(), eventList.createEventsToBoard());
        }
    }
    
    @Nested
    @DisplayName(TEST_CONSTRUCTOR)
    class ConstructorTests {
        
        @Test
        @DisplayName(TEST_CREATE_EMPTY_LIST)
        void testConstructorCreatesEmptyList() {
            EventList newList = new EventList();
            assertNotNull(newList);
            assertTrue(newList.getEvents().isEmpty());
        }
    }
    
    @Nested
    @DisplayName(TEST_ADD_EVENT)
    class AddEventTests {
        
        @Test
        @DisplayName(TEST_ADD_VALID_EVENT)
        void testAddValidEvent() {
            eventList.addEvent(event1);
            assertEquals(1, eventList.getEvents().size());
            assertTrue(eventList.getEvents().contains(event1));
        }
        
        @Test
        @DisplayName(TEST_ADD_MULTIPLE_EVENTS)
        void testAddMultipleEvents() {
            eventList.addEvent(event1);
            eventList.addEvent(event2);
            eventList.addEvent(event3);
            assertEquals(3, eventList.getEvents().size());
            assertTrue(eventList.getEvents().contains(event1));
            assertTrue(eventList.getEvents().contains(event2));
            assertTrue(eventList.getEvents().contains(event3));
        }
        
        @Test
        @DisplayName(TEST_ADD_NULL_EVENT)
        void testAddNullEvent() {
            assertThrows(IllegalArgumentException.class, 
                () -> eventList.addEvent(null));
        }
        
        @Test
        @DisplayName(TEST_ADD_DUPLICATE_NAME)
        void testAddDuplicateEventName() {
            eventList.addEvent(event1);
            Event duplicateEvent = new Event(TYPE_DIFFERENT, EVENT_REAL_MADRID_BARCELONA, futureTime.plusDays(1));
            
            assertThrows(IllegalArgumentException.class, 
                () -> eventList.addEvent(duplicateEvent));
        }
        
        @Test
        @DisplayName(TEST_MAINTAIN_ONE_AFTER_DUPLICATE)
        void testListSizeAfterDuplicateAttempt() {
            eventList.addEvent(event1);
            Event duplicateEvent = new Event(TYPE_DIFFERENT, EVENT_REAL_MADRID_BARCELONA, futureTime.plusDays(1));
            
            try {
                eventList.addEvent(duplicateEvent);
            } catch (IllegalArgumentException e) {
                // Expected exception
            }
            
            assertEquals(1, eventList.getEvents().size());
        }
    }
    
    @Nested
    @DisplayName(TEST_GET_EVENTS)
    class GetEventsTests {
        
        @Test
        @DisplayName(TEST_GET_EVENTS_EMPTY)
        void testGetEventsEmpty() {
            List<Event> events = eventList.getEvents();
            assertNotNull(events);
            assertTrue(events.isEmpty());
        }
        
        @Test
        @DisplayName(TEST_GET_EVENTS_WITH_DATA)
        void testGetEventsWithData() {
            eventList.addEvent(event1);
            eventList.addEvent(event2);
            
            List<Event> events = eventList.getEvents();
            assertEquals(2, events.size());
            assertTrue(events.contains(event1));
            assertTrue(events.contains(event2));
        }
        
        @Test
        @DisplayName(TEST_GET_EVENTS_DEFENSIVE_COPY)
        void testGetEventsReturnsDefensiveCopy() {
            eventList.addEvent(event1);
            eventList.addEvent(event2);
            
            List<Event> events = eventList.getEvents();
            events.clear();
            
            // Original list should not be affected
            assertEquals(2, eventList.getEvents().size());
        }
        
        @Test
        @DisplayName(TEST_GET_EVENTS_ADD_NO_AFFECT)
        void testGetEventsAddToReturnedList() {
            eventList.addEvent(event1);
            
            List<Event> events = eventList.getEvents();
            events.add(event2);
            
            // Original list should not be affected
            assertEquals(1, eventList.getEvents().size());
            assertFalse(eventList.getEvents().contains(event2));
        }
    }
    
    @Nested
    @DisplayName(TEST_GET_EVENT_BY_NAME)
    class GetEventByNameTests {
        
        @Test
        @DisplayName(TEST_GET_EVENT_BY_NAME_FOUND)
        void testGetEventByNameFound() {
            eventList.addEvent(event1);
            eventList.addEvent(event2);
            
            Event foundEvent = eventList.getEventByName(EVENT_REAL_MADRID_BARCELONA);
            assertNotNull(foundEvent);
            assertEquals(event1, foundEvent);
            assertEquals(EVENT_REAL_MADRID_BARCELONA, foundEvent.getName());
        }
        
        @Test
        @DisplayName(TEST_GET_EVENT_BY_NAME_MULTIPLE)
        void testGetEventByNameMultipleEvents() {
            eventList.addEvent(event1);
            eventList.addEvent(event2);
            eventList.addEvent(event3);
            
            Event foundEvent = eventList.getEventByName(EVENT_LAKERS_WARRIORS);
            assertNotNull(foundEvent);
            assertEquals(event2, foundEvent);
            assertEquals(TYPE_BASKETBALL, foundEvent.getType());
        }
        
        @Test
        @DisplayName(TEST_GET_EVENT_BY_NAME_NOT_FOUND)
        void testGetEventByNameNotFound() {
            eventList.addEvent(event1);
            
            assertThrows(IllegalArgumentException.class, 
                () -> eventList.getEventByName(EVENT_NONEXISTENT));
        }
        
        @Test
        @DisplayName(TEST_GET_EVENT_BY_NAME_EMPTY_LIST)
        void testGetEventByNameEmptyList() {
            assertThrows(IllegalArgumentException.class, 
                () -> eventList.getEventByName(EVENT_ANY));
        }
        
        @Test
        @DisplayName(TEST_GET_EVENT_BY_NAME_CASE_SENSITIVE)
        void testGetEventByNameCaseSensitive() {
            eventList.addEvent(event1);
            
            assertThrows(IllegalArgumentException.class, 
                () -> eventList.getEventByName(EVENT_REAL_MADRID_LOWERCASE));
        }
    }
    
    @Nested
    @DisplayName(TEST_TO_STRING)
    class ToStringTests {
        
        @Test
        @DisplayName(TEST_TO_STRING_EMPTY)
        void testToStringEmptyList() {
            String result = eventList.toString();
            assertNotNull(result);
            assertEquals(EMPTY_STRING, result);
        }
        
        @Test
        @DisplayName(TEST_TO_STRING_NO_BETS)
        void testToStringBlankWhenNoBets() {
            eventList.addEvent(event1);

            String result = eventList.toString();
            assertTrue(result.isBlank());
            assertEquals(Constants.NEWLINE, result);
        }
        
        @Test
        @DisplayName(TEST_TO_STRING_SINGLE_WITH_BET)
        void testToStringSingleEventWithBet() {
            event1.addBet("Win Bet", "Bettor1", 2.0f, 50.0f);
            eventList.addEvent(event1);
            Bet bet = event1.getBet("Bettor1");

            String expected = event1.eventToString() + Constants.SEPARATOR + bet.toString() + Constants.NEWLINE;
            assertEquals(expected, eventList.toString());
        }
        
        @Test
        @DisplayName(TEST_TO_STRING_MULTIPLE_WITH_BETS)
        void testToStringMultipleEventsWithBets() {
            event1.addBet("Win Bet", "Bettor1", 2.0f, 50.0f);
            event2.addBet("Score Bet", "Bettor2", 3.0f, 25.0f);
            eventList.addEvent(event1);
            eventList.addEvent(event2);
            
            StringBuilder expected = new StringBuilder();
            expected.append(event1.eventToString())
                    .append(Constants.SEPARATOR)
                    .append(event1.getBet("Bettor1").toString())
                    .append(Constants.NEWLINE);
            expected.append(event2.eventToString())
                    .append(Constants.SEPARATOR)
                    .append(event2.getBet("Bettor2").toString())
                    .append(Constants.NEWLINE);
            
            assertEquals(expected.toString(), eventList.toString());
        }
        
        @Test
        @DisplayName(TEST_TO_STRING_DELEGATES)
        void testToStringDelegatesToEventToString() {
            event1.addBet("Special Bet", "Bettor1", 1.8f, 30.0f);
            eventList.addEvent(event1);

            String listString = eventList.toString();
            assertTrue(listString.contains(event1.toString()));
        }
    }
    
    @Nested
    @DisplayName(TEST_INTEGRATION)
    class IntegrationTests {
        
        @Test
        @DisplayName(TEST_COMPLETE_WORKFLOW)
        void testCompleteWorkflow() {
            // Add events
            eventList.addEvent(event1);
            eventList.addEvent(event2);
            eventList.addEvent(event3);

            event1.addBet("Bet A", "Alice", 1.5f, 100.0f);
            event2.addBet("Bet B", "Bob", 2.0f, 50.0f);
            event3.addBet("Bet C", "Carol", 3.0f, 25.0f);
            
            // Verify size
            assertEquals(3, eventList.getEvents().size());
            
            // Retrieve specific event
            Event retrieved = eventList.getEventByName(EVENT_LAKERS_WARRIORS);
            assertEquals(TYPE_BASKETBALL, retrieved.getType());
            
            // Verify toString contains all events
            String output = eventList.toString();
            assertTrue(output.contains(EVENT_REAL_MADRID_BARCELONA));
            assertTrue(output.contains(EVENT_LAKERS_WARRIORS));
            assertTrue(output.contains(EVENT_NADAL_FEDERER));
        }
        
        @Test
        @DisplayName(TEST_DIFFERENT_TYPE_SAME_NAME)
        void testDifferentTypeSameName() {
            eventList.addEvent(event1);
            
            // Attempt to add event with same name but different type
            Event sameNameEvent = new Event(TYPE_TENNIS, EVENT_REAL_MADRID_BARCELONA, futureTime.plusDays(2));
            
            assertThrows(IllegalArgumentException.class, 
                () -> eventList.addEvent(sameNameEvent));
        }
        
        @Test
        @DisplayName(TEST_EVENT_INTEGRITY)
        void testEventIntegrity() {
            eventList.addEvent(event1);
            eventList.addEvent(event2);
            
            Event retrieved1 = eventList.getEventByName(EVENT_REAL_MADRID_BARCELONA);
            Event retrieved2 = eventList.getEventByName(EVENT_LAKERS_WARRIORS);
            
            assertSame(event1, retrieved1);
            assertSame(event2, retrieved2);
        }
    }
}
