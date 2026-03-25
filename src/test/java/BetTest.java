import classes.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Proves de la classe Bet")
public class BetTest {
    
    private static final String TEST_BET_CONSTRUCTOR_TESTS = "Proves del Constructor";
    private static final String TEST_BET_CREATE_VALID = "Ha de crear una aposta amb paràmetres vàlids";
    private static final String TEST_BET_NULL_BETTOR_NAME = "Ha de llançar excepció quan el nom de l'apostant és nul";
    private static final String TEST_BET_EMPTY_BETTOR_NAME = "Ha de llançar excepció quan el nom de l'apostant està buit";
    private static final String TEST_BET_NULL_DESCRIPTION = "Ha de llançar excepció quan la descripció és nul·la";
    private static final String TEST_BET_EMPTY_DESCRIPTION = "Ha de llançar excepció quan la descripció està buida";
    private static final String TEST_BET_ZERO_AMOUNT = "Ha de llançar excepció quan l'import és zero";
    private static final String TEST_BET_NEGATIVE_AMOUNT = "Ha de llançar excepció quan l'import és negatiu";
    private static final String TEST_BET_ZERO_ODDS = "Ha de llançar excepció quan les quotes són zero";
    private static final String TEST_BET_NEGATIVE_ODDS = "Ha de llançar excepció quan les quotes són negatives";
    private static final String TEST_BET_GETTER_TESTS = "Proves dels Getters";
    private static final String TEST_BET_GET_BETTOR_NAME = "Ha de retornar el nom de l'apostant correcte";
    private static final String TEST_BET_GET_DESCRIPTION = "Ha de retornar la descripció correcta";
    private static final String TEST_BET_GET_AMOUNT = "Ha de retornar l'import correcte";
    private static final String TEST_BET_TO_STRING_TESTS = "Proves de toString";
    private static final String TEST_BET_FORMAT_CSV = "Ha de formatar l'aposta com a cadena CSV";
    private static final String TEST_BET_SEPARATOR_VALUES = "Ha de separar els valors amb comes";
    private static final String TEST_BET_GET_ODDS = "Ha de retornar les quotes correctes";
    private static final String TEST_BET_FORMAT_DECIMALS = "Ha de formatar correctament decimals";
    
    // Test data constants
    private static final String TEST_BETTOR_1 = "John Doe";
    private static final String TEST_DESCRIPTION_1 = "Barcelona Win";
    private static final String TEST_BETTOR_2 = "Alice Smith";
    private static final String TEST_DESCRIPTION_2 = "Real Madrid Win";
    private static final String TEST_BETTOR_3 = "Player1";
    private static final String TEST_DESCRIPTION_3 = "Team A Win";
    private static final String TEST_BETTOR_4 = "Player2";
    private static final String TEST_DESCRIPTION_4 = "Over 2.5 Goals";
    private static final String TEST_BETTOR_5 = "Player3";
    private static final String TEST_DESCRIPTION_5 = "Draw Bet";
    private static final String TEST_ODDS_2_30 = "2.30";
    private static final String TEST_ODDS_150_00 = "150.00";
    private static final String TEST_ODDS_1_70 = "1.70";
    private static final String TEST_ODDS_1_80 = "1.80";
    private static final String TEST_AMOUNT_33_33 = "33.33";
    
    @Nested
    @DisplayName(TEST_BET_CONSTRUCTOR_TESTS)
    class ConstructorTests {
        
        @Test
        @DisplayName(TEST_BET_CREATE_VALID)
        void testValidConstructor() {
            Bet bet = new Bet(TEST_BETTOR_1, TEST_DESCRIPTION_1, 2.1f, 100.0f);
            assertNotNull(bet);
            assertEquals(TEST_BETTOR_1, bet.getBettorName());
            assertEquals(TEST_DESCRIPTION_1, bet.getBetDescription());
            assertEquals(2.1f, bet.getOdds());
            assertEquals(100.0f, bet.getAmount());
        }
        
        @Test
        @DisplayName(TEST_BET_NULL_BETTOR_NAME)
        void testConstructorNullBettorName() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Bet(null, TEST_DESCRIPTION_1, 2.0f, 100.0f));
        }
        
        @Test
        @DisplayName(TEST_BET_EMPTY_BETTOR_NAME)
        void testConstructorEmptyBettorName() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Bet("", TEST_DESCRIPTION_1, 2.0f, 100.0f));
        }
        
        @Test
        @DisplayName(TEST_BET_NULL_DESCRIPTION)
        void testConstructorNullDescription() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Bet(TEST_BETTOR_1, null, 2.0f, 100.0f));
        }
        
        @Test
        @DisplayName(TEST_BET_EMPTY_DESCRIPTION)
        void testConstructorEmptyDescription() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Bet(TEST_BETTOR_1, "", 2.0f, 100.0f));
        }

        @Test
        @DisplayName(TEST_BET_ZERO_ODDS)
        void testConstructorZeroOdds() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Bet(TEST_BETTOR_1, TEST_DESCRIPTION_1, 0f, 100.0f));
        }

        @Test
        @DisplayName(TEST_BET_NEGATIVE_ODDS)
        void testConstructorNegativeOdds() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Bet(TEST_BETTOR_1, TEST_DESCRIPTION_1, -1.5f, 100.0f));
        }
        
        @Test
        @DisplayName(TEST_BET_ZERO_AMOUNT)
        void testConstructorZeroAmount() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Bet(TEST_BETTOR_1, TEST_DESCRIPTION_1, 2.0f, 0f));
        }
        
        @Test
        @DisplayName(TEST_BET_NEGATIVE_AMOUNT)
        void testConstructorNegativeAmount() {
            assertThrows(IllegalArgumentException.class, 
                () -> new Bet(TEST_BETTOR_1, TEST_DESCRIPTION_1, 2.0f, -50.0f));
        }
    }
    
    @Nested
    @DisplayName(TEST_BET_GETTER_TESTS)
    class GetterTests {
        
        private Bet bet;
        
        void setUp() {
            bet = new Bet(TEST_BETTOR_2, TEST_DESCRIPTION_2, 1.9f, 250.0f);
        }
        
        @Test
        @DisplayName(TEST_BET_GET_BETTOR_NAME)
        void testGetBettorName() {
            setUp();
            assertEquals(TEST_BETTOR_2, bet.getBettorName());
        }
        
        @Test
        @DisplayName(TEST_BET_GET_DESCRIPTION)
        void testGetDescription() {
            setUp();
            assertEquals(TEST_DESCRIPTION_2, bet.getBetDescription());
        }
        
        @Test
        @DisplayName(TEST_BET_GET_ODDS)
        void testGetOdds() {
            setUp();
            assertEquals(1.9f, bet.getOdds());
        }

        @Test
        @DisplayName(TEST_BET_GET_AMOUNT)
        void testGetAmount() {
            setUp();
            assertEquals(250.0f, bet.getAmount());
        }
    }
    
    @Nested
    @DisplayName(TEST_BET_TO_STRING_TESTS)
    class ToStringTests {
        
        @Test
        @DisplayName(TEST_BET_FORMAT_CSV)
        void testToStringFormat() {
            Bet bet = new Bet(TEST_BETTOR_3, TEST_DESCRIPTION_3, 2.3f, 150.0f);
            String result = bet.toString();
            assertTrue(result.contains(TEST_BETTOR_3));
            assertTrue(result.contains(TEST_DESCRIPTION_3));
            assertTrue(result.contains(TEST_ODDS_2_30));
            assertTrue(result.contains(TEST_ODDS_150_00));
        }
        
        @Test
        @DisplayName(TEST_BET_SEPARATOR_VALUES)
        void testToStringSeparator() {
            Bet bet = new Bet(TEST_BETTOR_4, TEST_DESCRIPTION_4, 1.7f, 75.0f);
            String result = bet.toString();
            String[] parts = result.split(",");
            assertEquals(4, parts.length);
            assertEquals(TEST_BETTOR_4, parts[0]);
            assertEquals(TEST_DESCRIPTION_4, parts[1]);
            assertEquals(TEST_ODDS_1_70, parts[2]);
        }
        
        @Test
        @DisplayName(TEST_BET_FORMAT_DECIMALS)
        void testToStringFormatDecimals() {
            Bet bet = new Bet(TEST_BETTOR_5, TEST_DESCRIPTION_5, 1.8f, 33.33f);
            String result = bet.toString();
            assertTrue(result.contains(TEST_ODDS_1_80));
            assertTrue(result.contains(TEST_AMOUNT_33_33));
        }
    }
}
