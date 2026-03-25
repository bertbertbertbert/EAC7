import classes.Event;
import classes.Bet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Event Class Tests")
public class EventTest {
    
    // Display name constants
    private static final String TEST_CONSTRUCTOR = "Constructor Tests";
    private static final String TEST_CREATE_VALID = "Should create event with valid parameters";
    private static final String TEST_NULL_NAME = "Should throw exception when name is null";
    private static final String TEST_EMPTY_NAME = "Should throw exception when name is empty";
    private static final String TEST_NULL_BET_TYPE = "Should throw exception when betType is null";
    private static final String TEST_EMPTY_BET_TYPE = "Should throw exception when betType is empty";
    private static final String TEST_NULL_TIMESTAMP = "Should throw exception when timestamp is null";
    private static final String TEST_PAST_TIMESTAMP = "Should throw exception when timestamp is in the past";
    private static final String TEST_NOW_TIMESTAMP = "Should throw exception when timestamp is now";
    private static final String TEST_GETTER = "Getter Tests";
    private static final String TEST_GET_NAME = "Should return correct name";
    private static final String TEST_GET_BET_TYPE = "Should return correct bet type";
    private static final String TEST_GET_TIMESTAMP = "Should return correct timestamp";
    private static final String TEST_SETTER = "Setter Tests";
    private static final String TEST_SET_TYPE = "setType Tests";
    private static final String TEST_SET_TYPE_VALID = "Should update type with valid value";
    private static final String TEST_SET_NAME_NULL = "Should throw exception when setting null name";
    private static final String TEST_SET_NAME_EMPTY = "Should throw exception when setting empty name";
    private static final String TEST_SET_NAME = "setName Tests";
    private static final String TEST_SET_NAME_VALID = "Should update name with valid value";
    private static final String TEST_SET_BET_TYPE_NULL = "Should throw exception when setting null bet type";
    private static final String TEST_SET_BET_TYPE_EMPTY = "Should throw exception when setting empty bet type";
    private static final String TEST_SET_TIMESTAMP = "setTimestamp Tests";
    private static final String TEST_SET_TIMESTAMP_VALID = "Should update timestamp with valid future time";
    private static final String TEST_SET_TIMESTAMP_NULL = "Should throw exception when setting null timestamp";
    private static final String TEST_SET_TIMESTAMP_PAST = "Should throw exception when setting past timestamp";
    private static final String TEST_SET_TIMESTAMP_CURRENT = "Should throw exception when setting current timestamp";
    private static final String TEST_BET_MANAGEMENT = "Bet Management Tests";
    private static final String TEST_ADD_BET = "addBet Tests";
    private static final String TEST_ADD_BET_VALID = "Should add bet with valid parameters";
    private static final String TEST_ADD_MULTIPLE_BETS = "Should add multiple bets from different bettors";
    private static final String TEST_ADD_BET_DUPLICATE = "Should throw when adding bet for existing bettor";
    private static final String TEST_ADD_BET_NULL_BETTOR = "Should throw exception when bettor is null";
    private static final String TEST_ADD_BET_EMPTY_BETTOR = "Should throw exception when bettor is empty";
    private static final String TEST_ADD_BET_ZERO_AMOUNT = "Should throw exception when amount is zero";
    private static final String TEST_ADD_BET_NEGATIVE_AMOUNT = "Should throw exception when amount is negative";
    private static final String TEST_ADD_BET_NULL_DESCRIPTION = "Should throw exception when bet description is null";
    private static final String TEST_ADD_BET_EMPTY_DESCRIPTION = "Should throw exception when bet description is empty";
    private static final String TEST_ADD_BET_ZERO_ODDS = "Should throw exception when odds is zero";
    private static final String TEST_ADD_BET_NEGATIVE_ODDS = "Should throw exception when odds is negative";
    private static final String TEST_GET_BET = "getBet Tests";
    private static final String TEST_GET_BET_EXISTING = "Should return bet object for existing bettor";
    private static final String TEST_GET_BET_NOT_FOUND = "Should throw exception when bettor not found";
    private static final String TEST_GET_BET_NULL_BETTOR = "Should throw exception when bettor is null";
    private static final String TEST_GET_BET_EMPTY_BETTOR = "Should throw exception when bettor is empty";
    private static final String TEST_BETTOR_EXISTS = "bettorExists Tests";
    private static final String TEST_BETTOR_EXISTS_TRUE = "Should return true when bettor exists";
    private static final String TEST_BETTOR_EXISTS_FALSE = "Should return false when bettor does not exist";
    private static final String TEST_BETTOR_EXISTS_NULL = "Should throw exception when bettor is null";
    private static final String TEST_BETTOR_EXISTS_EMPTY = "Should throw exception when bettor is empty";
    private static final String TEST_GET_ALL_BETS = "getAllBets Tests";
    private static final String TEST_GET_ALL_BETS_EMPTY = "Should return empty map when no bets";
    private static final String TEST_GET_ALL_BETS_MULTIPLE = "Should return map with all bets";
    private static final String TEST_BETS_TO_STRING = "betsToString should format bets and include newlines";
    private static final String TEST_TO_STRING = "toString Tests";
    private static final String TEST_TO_STRING_FORMAT = "Should format event as CSV string";
    private static final String TEST_TO_STRING_SEPARATOR = "Should separate values with commas";
    
    // Test data constants
    private static final String TYPE_SOCCER_MATCH = "Soccer Match";
    private static final String TYPE_TENNIS_MATCH = "Tennis Match";
    private static final String TYPE_BASKETBALL_GAME = "Basketball Game";
    private static final String TYPE_ORIGINAL_EVENT = "Original Event";
    private static final String TYPE_UPDATED_EVENT = "Updated Event";
    private static final String TYPE_SOCCER = "Soccer";
    private static final String NAME_OSASUNA_BARCELONA = "Osasuna - Barcelona";
    private static final String NAME_NADAL_ALCARAZ = "Nadal Vs Alcaraz";
    private static final String NAME_BASKONIA_ESTUDIANTES = "Baskonia Vs Estudiantes";
    private static final String NAME_WIN_LOSS = "Win/Loss";
    private static final String NAME_HANDICAP = "Handicap";
    private static final String NAME_WIN = "Win";
    private static final String PLAYER_1 = "Player1";
    private static final String PLAYER_2 = "Player2";
    private static final String PLAYER_3 = "Player3";
    private static final String BETTOR_NONEXISTENT = "NonExistent";
    private static final String BET_WIN = "Win Bet";
    private static final String BET_A = "Bet A";
    private static final String BET_B = "Bet B";
    private static final String BET_C = "Bet C";
    private static final String BET_FIRST = "First Bet";
    private static final String BET_SECOND = "Second Bet";
    private static final String BET_GENERIC = "Bet";
    private static final String BET_MY = "My Bet";
    private static final String BET_TEST = "Test Bet";
    private static final String BET_X = "Bet X";
    private static final String BET_Y = "Bet Y";
    private static final String ODDS_2_10 = "2.10";
    private static final String ODDS_1_60 = "1.60";
    private static final String AMOUNT_100_00 = "100.00";
    private static final String AMOUNT_50_00 = "50.00";
    private static final String EMPTY_STRING = "";
    private static final String NEWLINE = "\n";
    
    private LocalDateTime futureTime;
    
    @BeforeEach
    void setUp() {
        futureTime = LocalDateTime.now().plusDays(1).plusHours(1);
    }
    
    @Nested
    @DisplayName(TEST_CONSTRUCTOR)
    class ConstructorTests {
        
        @Test
        @DisplayName(TEST_CREATE_VALID)
        void testValidConstructor() {
            Event event = new Event(TYPE_SOCCER_MATCH, NAME_OSASUNA_BARCELONA, futureTime);
            assertNotNull(event);
            assertEquals(TYPE_SOCCER_MATCH, event.getType());
            assertEquals(NAME_OSASUNA_BARCELONA, event.getName());
            assertEquals(futureTime, event.getTimestamp());
        }
        
        @Test
        @DisplayName(TEST_NULL_NAME)
        void testConstructorNullName() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Event(null, NAME_WIN_LOSS, futureTime));
        }
        
        @Test
        @DisplayName(TEST_EMPTY_NAME)
        void testConstructorEmptyName() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Event(EMPTY_STRING, NAME_WIN_LOSS, futureTime));
        }
        
        @Test
        @DisplayName(TEST_NULL_BET_TYPE)
        void testConstructorNullBetType() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Event(TYPE_SOCCER_MATCH, null, futureTime));
        }
        
        @Test
        @DisplayName(TEST_EMPTY_BET_TYPE)
        void testConstructorEmptyBetType() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Event(TYPE_SOCCER_MATCH, EMPTY_STRING, futureTime));
        }
        
        @Test
        @DisplayName(TEST_NULL_TIMESTAMP)
        void testConstructorNullTimestamp() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Event(TYPE_SOCCER_MATCH, NAME_WIN_LOSS, null));
        }
        
        @Test
        @DisplayName(TEST_PAST_TIMESTAMP)
        void testConstructorPastTimestamp() {
            LocalDateTime pastTime = LocalDateTime.now().minusHours(1);
            assertThrows(IllegalArgumentException.class, 
                () -> new Event(TYPE_SOCCER_MATCH, NAME_WIN_LOSS, pastTime));
        }
        
        @Test
        @DisplayName(TEST_NOW_TIMESTAMP)
        void testConstructorCurrentTimestamp() {
            LocalDateTime currentTime = LocalDateTime.now();
            assertThrows(IllegalArgumentException.class, 
                () -> new Event(TYPE_SOCCER_MATCH, NAME_WIN_LOSS, currentTime));
        }
    }
    
    @Nested
    @DisplayName(TEST_GETTER)
    class GetterTests {
        
        private Event event;
        
        @BeforeEach
        void setUp() {
            event = new Event(TYPE_TENNIS_MATCH, NAME_NADAL_ALCARAZ, futureTime);
        }
        
        @Test
        @DisplayName(TEST_GET_NAME)
        void testGetName() {
            assertEquals(TYPE_TENNIS_MATCH, event.getType());
        }
        
        @Test
        @DisplayName(TEST_GET_BET_TYPE)
        void testGetBetType() {
            assertEquals(NAME_NADAL_ALCARAZ, event.getName());
        }
        
        @Test
        @DisplayName(TEST_GET_TIMESTAMP)
        void testGetTimestamp() {
            assertEquals(futureTime, event.getTimestamp());
        }
    }
    
    @Nested
    @DisplayName(TEST_SETTER)
    class SetterTests {
        
        private Event event;
        
        @BeforeEach
        void setUp() {
            event = new Event(TYPE_ORIGINAL_EVENT, NAME_WIN_LOSS, futureTime);
        }
        
        @Nested
        @DisplayName(TEST_SET_TYPE)
        class SetTypeTests {
            
            @Test
            @DisplayName(TEST_SET_TYPE_VALID)
            void testSetTypeValid() {
                event.setType(TYPE_UPDATED_EVENT);
                assertEquals(TYPE_UPDATED_EVENT, event.getType());
            }
            
            @Test
            @DisplayName(TEST_SET_NAME_NULL)
            void testSetNameNull() {
                assertThrows(IllegalArgumentException.class, () -> event.setType(null));
            }
            
            @Test
            @DisplayName(TEST_SET_NAME_EMPTY)
            void testSetNameEmpty() {
                assertThrows(IllegalArgumentException.class, () -> event.setType(EMPTY_STRING));
            }
        }
        
        @Nested
        @DisplayName(TEST_SET_NAME)
        class SetNameTests {
            
            @Test
            @DisplayName(TEST_SET_NAME_VALID)
            void testSetNameValid() {
                event.setName(NAME_HANDICAP);
                assertEquals(NAME_HANDICAP, event.getName());
            }
            
            @Test
            @DisplayName(TEST_SET_BET_TYPE_NULL)
            void testSetBetTypeNull() {
                assertThrows(IllegalArgumentException.class, () -> event.setName(null));
            }
            
            @Test
            @DisplayName(TEST_SET_BET_TYPE_EMPTY)
            void testSetBetTypeEmpty() {
                assertThrows(IllegalArgumentException.class, () -> event.setName(EMPTY_STRING));
            }
        }
                
        @Nested
        @DisplayName(TEST_SET_TIMESTAMP)
        class SetTimestampTests {
            
            @Test
            @DisplayName(TEST_SET_TIMESTAMP_VALID)
            void testSetTimestampValid() {
                LocalDateTime newFutureTime = LocalDateTime.now().plusHours(2);
                event.setTimestamp(newFutureTime);
                assertEquals(newFutureTime, event.getTimestamp());
            }
            
            @Test
            @DisplayName(TEST_SET_TIMESTAMP_NULL)
            void testSetTimestampNull() {
                assertThrows(IllegalArgumentException.class, () -> event.setTimestamp(null));
            }
            
            @Test
            @DisplayName(TEST_SET_TIMESTAMP_PAST)
            void testSetTimestampPast() {
                LocalDateTime pastTime = LocalDateTime.now().minusHours(1);
                assertThrows(IllegalArgumentException.class, () -> event.setTimestamp(pastTime));
            }
            
            @Test
            @DisplayName(TEST_SET_TIMESTAMP_CURRENT)
            void testSetTimestampCurrent() {
                LocalDateTime currentTime = LocalDateTime.now();
                assertThrows(IllegalArgumentException.class, () -> event.setTimestamp(currentTime));
            }
        }
    }
    
    @Nested
    @DisplayName(TEST_BET_MANAGEMENT)
    class BetManagementTests {
        
        private Event event;
        
        @BeforeEach
        void setUp() {
            event = new Event(TYPE_BASKETBALL_GAME, NAME_BASKONIA_ESTUDIANTES, futureTime);
        }
        
        @Nested
        @DisplayName(TEST_ADD_BET)
        class AddBetTests {
            
            @Test
            @DisplayName(TEST_ADD_BET_VALID)
            void testAddBetValid() {
                event.addBet(BET_WIN, PLAYER_1, 1.5f, 100.0f);
                assertTrue(event.bettorExists(PLAYER_1));
                Bet bet = event.getBet(PLAYER_1);
                assertEquals(PLAYER_1, bet.getBettorName());
                assertEquals(BET_WIN, bet.getBetDescription());
                assertEquals(1.5f, bet.getOdds());
                assertEquals(100.0f, bet.getAmount());
            }
            
            @Test
            @DisplayName(TEST_ADD_MULTIPLE_BETS)
            void testAddMultipleBets() {
                event.addBet(BET_A, PLAYER_1, 2.0f, 100.0f);
                event.addBet(BET_B, PLAYER_2, 3.0f, 50.0f);
                event.addBet(BET_C, PLAYER_3, 1.8f, 75.0f);
                assertTrue(event.bettorExists(PLAYER_1));
                assertTrue(event.bettorExists(PLAYER_2));
                assertTrue(event.bettorExists(PLAYER_3));
                assertEquals(100.0f, event.getBet(PLAYER_1).getAmount());
                assertEquals(50.0f, event.getBet(PLAYER_2).getAmount());
                assertEquals(75.0f, event.getBet(PLAYER_3).getAmount());
            }
            
            @Test
            @DisplayName(TEST_ADD_BET_DUPLICATE)
            void testAddBetDuplicateBettor() {
                event.addBet(BET_FIRST, PLAYER_1, 1.5f, 100.0f);
                assertThrows(IllegalArgumentException.class, () ->
                        event.addBet(BET_SECOND, PLAYER_1, 2.0f, 150.0f));
                // Original bet remains intact
                Bet original = event.getBet(PLAYER_1);
                assertEquals(100.0f, original.getAmount());
                assertEquals(BET_FIRST, original.getBetDescription());
                assertEquals(1.5f, original.getOdds());
            }
            
            @Test
            @DisplayName(TEST_ADD_BET_NULL_BETTOR)
            void testAddBetNullBettor() {
                assertThrows(IllegalArgumentException.class, () -> event.addBet(BET_GENERIC, null, 1.5f, 100.0f));
            }
            
            @Test
            @DisplayName(TEST_ADD_BET_EMPTY_BETTOR)
            void testAddBetEmptyBettor() {
                assertThrows(IllegalArgumentException.class, () -> event.addBet(BET_GENERIC, EMPTY_STRING, 1.5f, 100.0f));
            }
            
            @Test
            @DisplayName(TEST_ADD_BET_ZERO_AMOUNT)
            void testAddBetZeroAmount() {
                assertThrows(IllegalArgumentException.class, () -> event.addBet(BET_GENERIC, PLAYER_1, 1.5f, 0f));
            }
            
            @Test
            @DisplayName(TEST_ADD_BET_NEGATIVE_AMOUNT)
            void testAddBetNegativeAmount() {
                assertThrows(IllegalArgumentException.class, () -> event.addBet(BET_GENERIC, PLAYER_1, 1.5f, -50.0f));
            }            
            @Test
            @DisplayName(TEST_ADD_BET_NULL_DESCRIPTION)
            void testAddBetNullDescription() {
                assertThrows(IllegalArgumentException.class, () -> event.addBet(null, PLAYER_1, 1.5f, 100.0f));
            }
            
            @Test
            @DisplayName(TEST_ADD_BET_EMPTY_DESCRIPTION)
            void testAddBetEmptyDescription() {
                assertThrows(IllegalArgumentException.class, () -> event.addBet(EMPTY_STRING, PLAYER_1, 1.5f, 100.0f));
            }

            @Test
            @DisplayName(TEST_ADD_BET_ZERO_ODDS)
            void testAddBetZeroOdds() {
                assertThrows(IllegalArgumentException.class, () -> event.addBet(BET_GENERIC, PLAYER_1, 0f, 100.0f));
            }

            @Test
            @DisplayName(TEST_ADD_BET_NEGATIVE_ODDS)
            void testAddBetNegativeOdds() {
                assertThrows(IllegalArgumentException.class, () -> event.addBet(BET_GENERIC, PLAYER_1, -1.2f, 100.0f));
            }
        }
        
        @Nested
        @DisplayName(TEST_GET_BET)
        class GetBetTests {
            
            @Test
            @DisplayName(TEST_GET_BET_EXISTING)
            void testGetBetExisting() {
                event.addBet(BET_MY, PLAYER_1, 2.0f, 100.0f);
                Bet bet = event.getBet(PLAYER_1);
                assertNotNull(bet);
                assertEquals(PLAYER_1, bet.getBettorName());
                assertEquals(BET_MY, bet.getBetDescription());
                assertEquals(2.0f, bet.getOdds());
                assertEquals(100.0f, bet.getAmount());
            }
            
            @Test
            @DisplayName(TEST_GET_BET_NOT_FOUND)
            void testGetBetNotFound() {
                assertThrows(IllegalArgumentException.class, () -> event.getBet(BETTOR_NONEXISTENT));
            }
            
            @Test
            @DisplayName(TEST_GET_BET_NULL_BETTOR)
            void testGetBetNullBettor() {
                assertThrows(IllegalArgumentException.class, () -> event.getBet(null));
            }
            
            @Test
            @DisplayName(TEST_GET_BET_EMPTY_BETTOR)
            void testGetBetEmptyBettor() {
                assertThrows(IllegalArgumentException.class, () -> event.getBet(EMPTY_STRING));
            }
        }
        
        @Nested
        @DisplayName(TEST_BETTOR_EXISTS)
        class BettorExistsTests {
            
            @Test
            @DisplayName(TEST_BETTOR_EXISTS_TRUE)
            void testBettorExistsTrue() {
                event.addBet(BET_TEST, PLAYER_1, 1.7f, 100.0f);
                assertTrue(event.bettorExists(PLAYER_1));
            }
            
            @Test
            @DisplayName(TEST_BETTOR_EXISTS_FALSE)
            void testBettorExistsFalse() {
                assertFalse(event.bettorExists(BETTOR_NONEXISTENT));
            }
            
            @Test
            @DisplayName(TEST_BETTOR_EXISTS_NULL)
            void testBettorExistsNull() {
                assertThrows(IllegalArgumentException.class, () -> event.bettorExists(null));
            }
            
            @Test
            @DisplayName(TEST_BETTOR_EXISTS_EMPTY)
            void testBettorExistsEmpty() {
                assertThrows(IllegalArgumentException.class, () -> event.bettorExists(EMPTY_STRING));
            }
        }
        
        @Nested
        @DisplayName(TEST_GET_ALL_BETS)
        class GetAllBetsTests {
            
            @Test
            @DisplayName(TEST_GET_ALL_BETS_EMPTY)
            void testGetAllBetsEmpty() {
                assertTrue(event.getAllBets().isEmpty());
            }
            
            @Test
            @DisplayName(TEST_GET_ALL_BETS_MULTIPLE)
            void testGetAllBetsMultiple() {
                event.addBet(BET_A, PLAYER_1, 2.5f, 100.0f);
                event.addBet(BET_B, PLAYER_2, 1.9f, 50.0f);
                
                Map<String, Bet> allBets = event.getAllBets();
                assertEquals(2, allBets.size());
                assertTrue(allBets.containsKey(PLAYER_1));
                assertTrue(allBets.containsKey(PLAYER_2));
                assertEquals(2.5f, allBets.get(PLAYER_1).getOdds());
                assertEquals(1.9f, allBets.get(PLAYER_2).getOdds());
                assertEquals(100.0f, allBets.get(PLAYER_1).getAmount());
                assertEquals(50.0f, allBets.get(PLAYER_2).getAmount());
            }
            
            @Test
            @DisplayName(TEST_BETS_TO_STRING)
            void testBetsToStringFormatting() {
                event.addBet(BET_X, PLAYER_1, 2.1f, 100.0f);
                event.addBet(BET_Y, PLAYER_2, 1.6f, 50.0f);
                
                String allBetsString = event.betsToString();
                assertTrue(allBetsString.contains(PLAYER_1));
                assertTrue(allBetsString.contains(PLAYER_2));
                assertTrue(allBetsString.contains(BET_X));
                assertTrue(allBetsString.contains(BET_Y));
                assertTrue(allBetsString.contains(ODDS_2_10));
                assertTrue(allBetsString.contains(ODDS_1_60));
                assertTrue(allBetsString.contains(AMOUNT_100_00));
                assertTrue(allBetsString.contains(AMOUNT_50_00));
                assertTrue(allBetsString.contains(NEWLINE));
            }
        }
    }
    
    @Nested
    @DisplayName(TEST_TO_STRING)
    class ToStringTests {
        
        @Test
        @DisplayName(TEST_TO_STRING_FORMAT)
        void testToStringFormat() {
            Event event = new Event(TYPE_SOCCER, NAME_WIN, futureTime);
            String result = event.eventToString();
            assertTrue(result.contains(TYPE_SOCCER));
            assertTrue(result.contains(NAME_WIN));
            assertTrue(result.contains(futureTime.toString()));
        }
        
        @Test
        @DisplayName(TEST_TO_STRING_SEPARATOR)
        void testToStringSeparator() {
            Event event = new Event(TYPE_SOCCER, NAME_WIN, futureTime);
            String result = event.eventToString();
            String[] parts = result.split(",");
            assertEquals(3, parts.length);
        }

        @Test
        @DisplayName("toString returns empty when there are no bets")
        void testToStringEmptyWhenNoBets() {
            Event event = new Event(TYPE_SOCCER, NAME_WIN, futureTime);
            String result = event.toString();
            assertEquals("", result);
        }

        @Test
        @DisplayName("toString formats event and bets when bets exist")
        void testToStringWithBets() {
            Event event = new Event(TYPE_SOCCER, NAME_WIN, futureTime);
            event.addBet(BET_MY, PLAYER_1, 2.0f, 100.0f);
            String result = event.toString();
            // Should contain the event CSV followed by bet representation and newline
            assertTrue(result.contains(event.eventToString()));
            assertTrue(result.contains(PLAYER_1));
            assertTrue(result.contains(BET_MY));
            assertTrue(result.contains(ODDS_2_10) || result.contains("2.0") );
            assertTrue(result.contains(AMOUNT_100_00) || result.contains("100.0") );
        }
    }
}
