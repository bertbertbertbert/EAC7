import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import utils.UtilsIO;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 *
 * @author IOC
 */
@DisplayName("UtilsIO")
public class UtilsIOTest {

    // Input/Output message constants
    private final static String MESSAGE_ASK_ANYTHING = "Introdueixi alguna cosa";
    private final static String MESSAGE_ERROR = "Error en la entrada";
    private final static String MESSAGE_TEST_INPUT = "This message tests the input";
    private final static float FLOAT_TEST_INPUT = 6.3f;
    private final static int INT_TEST_INPUT = 6;
    
    // Display name constants
    private static final String TEST_SHOW_ANY_MESSAGE = "showAnyMessage";
    private static final String TEST_PRINTS_HEADER_CONTENT = "prints header and content";
    private static final String TEST_NO_EXCEPTION_NULL_EMPTY = "no exception when header/content null or empty";
    private static final String TEST_SHOW_MENU = "showMenu";
    private static final String TEST_PRINTS_MENU_CONTENT = "prints menu content";
    private static final String TEST_NO_EXCEPTION_NULL_EMPTY_CONTENT = "no exception on null/empty content";
    private static final String TEST_SHOW_ERROR = "showError";
    private static final String TEST_PRINTS_ERROR_CONTENT = "prints error content";
    private static final String TEST_SHOW_INFO = "showInfo";
    private static final String TEST_PRINTS_INFO_CONTENT = "prints info content";
    private static final String TEST_ASK_STRING = "askForAnyString / askForNotEmptyString";
    private static final String TEST_READS_ANY_STRING = "reads any string";
    private static final String TEST_READS_NON_EMPTY_RETRY = "reads non-empty string after empty retry";
    private static final String TEST_USES_DEFAULTS_MESSAGES = "uses defaults when messages null or empty";
    private static final String TEST_ASK_INTEGER = "askForInteger";
    private static final String TEST_READS_INTEGER = "reads integer";
    private static final String TEST_RETRIES_NON_INTEGER = "retries on non-integer input";
    private static final String TEST_ASK_FLOAT = "askForFloat";
    private static final String TEST_READS_FLOAT = "reads float";
    private static final String TEST_RETRIES_NON_FLOAT = "retries on non-float input";
    private static final String TEST_ACCEPTS_INTEGER_AS_FLOAT = "accepts integer as float";
    private static final String TEST_SHOW_BETS = "showBets";
    private static final String TEST_THROWS_NULL_EMPTY = "throws on null or empty input";
    private static final String TEST_IGNORES_MALFORMED = "ignores malformed rows";
    private static final String TEST_PRINTS_VALID_ROWS = "prints valid rows";
    private static final String TEST_SKIPS_BAD_ODDS = "skips rows with bad odds";
    private static final String TEST_SKIPS_BAD_AMOUNT = "skips rows with bad amount";
    private static final String TEST_SHOWS_VALID_MIXED = "shows only valid rows when mixed";
    
    // Test data constants
    private static final String HEADER_TEST = "Test Header";
    private static final String CONTENT_TEST = "Test Content";
    private static final String CONTENT_MENU = "Test Menu Content";
    private static final String MESSAGE_ERROR_TEST = "Test Error Message";
    private static final String MESSAGE_INFO_TEST = "Test Info Message";
    private static final String HEADER = "header";
    private static final String CONTENT = "content";
    private static final String EMPTY_STRING = "";
    private static final String NEWLINE = "\n";
    
    // Bet test data constants
    private static final String RANDOM_STRING = "This is a random String without data";
    private static final String MALFORMED_DATA_1 = "DateTime1,Supermarket1,City1,1,1\nDateTime2,Supermarket2,City2,1,1\nDateTime3,Supermarket3,City3,1,1\nDateTime4,Supermarket4,1\n";
    private static final String MALFORMED_DATA_2 = "DateTime1,Supermarket1,City1,text_instead_of_float,1\nDateTime2,Supermarket2,City2,1,1\nDateTime3,Supermarket3,City3,1,1\nDateTime4,Supermarket4,1,1\n";
    
    // Valid bet data
    private static final String SPORT_SOCCER = "Soccer";
    private static final String SPORT_BASKETBALL = "Basketball";
    private static final String SPORT_TENNIS = "Tennis";
    private static final String SPORT_BASEBALL = "Baseball";
    private static final String EVENT_CHAMPIONSHIP_FINAL = "Championship Final";
    private static final String EVENT_LEAGUE_MATCH = "League Match";
    private static final String EVENT_ONE = "Event One";
    private static final String EVENT_TWO = "Event Two";
    private static final String EVENT_THREE = "Event Three";
    private static final String EVENT_FOUR = "Event Four";
    private static final String DATE_202312011200 = "202312011200";
    private static final String DATE_202312021200 = "202312021200";
    private static final String DATE_202312021300 = "202312021300";
    private static final String DATE_202312031200 = "202312031200";
    private static final String DATE_202312041200 = "202312041200";
    private static final String BETTOR_JOHN_DOE = "John Doe";
    private static final String BETTOR_JANE_SMITH = "Jane Smith";
    private static final String BETTOR_BOB_JOHNSON = "Bob Johnson";
    private static final String BETTOR_ALICE_BROWN = "Alice Brown";
    private static final String BETTOR_JOHN = "John";
    private static final String BETTOR_JANE = "Jane";
    private static final String BETTOR_BOB = "Bob";
    private static final String BETTOR_ALICE = "Alice";
    private static final String BET_HOME_WIN = "Home win";
    private static final String BET_OVER_150 = "Over 150.5";
    private static final String BET_WIN = "Win";
    private static final String BET_UNDER = "Under";
    private static final String ODDS_1_8 = "1.8";
    private static final String ODDS_2_0 = "2.0";
    private static final String ODDS_2_5 = "2.5";
    private static final String AMOUNT_75_50 = "75.50";
    private static final String AMOUNT_75_5 = "75.5";
    private static final String AMOUNT_65_2 = "65.2";
    private static final String AMOUNT_80_0 = "80.0";
    private static final String AMOUNT_60_5 = "60.5";
    private static final String INVALID_ODDS = "invalid_odds";
    private static final String INVALID_AMOUNT = "invalid_amount";
    private static final String INVALID_DATA = "InvalidDataWithoutCommas";   

    @BeforeAll
    public static void setUpClass() {
        Locale.setDefault(Locale.US);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    @BeforeEach
    public void setUp() {
        provideInput(EMPTY_STRING);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private UtilsIO ioWithInput(String data) {
        provideInput(data);
        return new UtilsIO();
    }

    @Nested
    @DisplayName(TEST_SHOW_ANY_MESSAGE)
    class ShowAnyMessageTests {

        @Test
        @DisplayName(TEST_PRINTS_HEADER_CONTENT)
        void printsHeaderAndContent() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            UtilsIO io = new UtilsIO();
            io.showAnyMessage(HEADER_TEST, CONTENT_TEST);

            String text = output.toString();
            assertTrue(text.contains(HEADER_TEST));
            assertTrue(text.contains(CONTENT_TEST));
        }

        @Test
        @DisplayName(TEST_NO_EXCEPTION_NULL_EMPTY)
        void throwsOnNullOrEmpty() {
            UtilsIO io = new UtilsIO();
            assertThrows(IllegalArgumentException.class, () -> io.showAnyMessage(null, CONTENT));
            assertThrows(IllegalArgumentException.class, () -> io.showAnyMessage(EMPTY_STRING, CONTENT));
            assertThrows(IllegalArgumentException.class, () -> io.showAnyMessage(HEADER, null));
            assertThrows(IllegalArgumentException.class, () -> io.showAnyMessage(HEADER, EMPTY_STRING));
        }
    }

    @Nested
    @DisplayName(TEST_SHOW_MENU)
    class ShowMenuTests {

        @Test
        @DisplayName(TEST_PRINTS_MENU_CONTENT)
        void printsMenuContent() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            UtilsIO io = new UtilsIO();
            io.showMenu(CONTENT_MENU);

            assertTrue(output.toString().contains(CONTENT_MENU));
        }

        @Test
        @DisplayName(TEST_NO_EXCEPTION_NULL_EMPTY_CONTENT)
        void throwsOnNullOrEmpty() {
            UtilsIO io = new UtilsIO();
            assertThrows(IllegalArgumentException.class, () -> io.showMenu(null));
            assertThrows(IllegalArgumentException.class, () -> io.showMenu(EMPTY_STRING));
        }
    }

    @Nested
    @DisplayName(TEST_SHOW_ERROR)
    class ShowErrorTests {

        @Test
        @DisplayName(TEST_PRINTS_ERROR_CONTENT)
        void printsErrorContent() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            UtilsIO io = new UtilsIO();
            io.showError(MESSAGE_ERROR_TEST);

            assertTrue(output.toString().contains(MESSAGE_ERROR_TEST));
        }

        @Test
        @DisplayName(TEST_NO_EXCEPTION_NULL_EMPTY_CONTENT)
        void throwsOnNullOrEmpty() {
            UtilsIO io = new UtilsIO();
            assertThrows(IllegalArgumentException.class, () -> io.showError(null));
            assertThrows(IllegalArgumentException.class, () -> io.showError(EMPTY_STRING));
        }
    }

    @Nested
    @DisplayName(TEST_SHOW_INFO)
    class ShowInfoTests {

        @Test
        @DisplayName(TEST_PRINTS_INFO_CONTENT)
        void printsInfoContent() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            UtilsIO io = new UtilsIO();
            io.showInfo(MESSAGE_INFO_TEST);

            assertTrue(output.toString().contains(MESSAGE_INFO_TEST));
        }

        @Test
        @DisplayName(TEST_NO_EXCEPTION_NULL_EMPTY_CONTENT)
        void throwsOnNullOrEmpty() {
            UtilsIO io = new UtilsIO();
            assertThrows(IllegalArgumentException.class, () -> io.showInfo(null));
            assertThrows(IllegalArgumentException.class, () -> io.showInfo(EMPTY_STRING));
        }
    }

    @Nested
    @DisplayName(TEST_ASK_STRING)
    class AskStringTests {

        @Test
        @DisplayName(TEST_READS_ANY_STRING)
        void askForAnyString() {
            UtilsIO io = ioWithInput(MESSAGE_TEST_INPUT);
            assertEquals(MESSAGE_TEST_INPUT, io.askForAnyString(MESSAGE_ASK_ANYTHING));
        }

        @Test
        @DisplayName(TEST_READS_NON_EMPTY_RETRY)
        void askForNotEmptyStringRetry() {
            UtilsIO io = ioWithInput(NEWLINE + MESSAGE_TEST_INPUT);
            assertEquals(MESSAGE_TEST_INPUT, io.askForNotEmptyString(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR));
        }

        @Test
        @DisplayName(TEST_USES_DEFAULTS_MESSAGES)
        void askForStringNullEmptyMessages() {
            assertEquals(MESSAGE_TEST_INPUT, ioWithInput(MESSAGE_TEST_INPUT).askForAnyString(null));
            assertEquals(MESSAGE_TEST_INPUT, ioWithInput(MESSAGE_TEST_INPUT).askForAnyString(EMPTY_STRING));

            UtilsIO io = ioWithInput(NEWLINE + MESSAGE_TEST_INPUT);
            assertEquals(MESSAGE_TEST_INPUT, io.askForNotEmptyString(null, MESSAGE_ERROR));
            assertEquals(MESSAGE_TEST_INPUT, ioWithInput(NEWLINE + MESSAGE_TEST_INPUT).askForNotEmptyString(EMPTY_STRING, MESSAGE_ERROR));
            assertEquals(MESSAGE_TEST_INPUT, ioWithInput(NEWLINE + MESSAGE_TEST_INPUT).askForNotEmptyString(MESSAGE_ASK_ANYTHING, null));
            assertEquals(MESSAGE_TEST_INPUT, ioWithInput(NEWLINE + MESSAGE_TEST_INPUT).askForNotEmptyString(MESSAGE_ASK_ANYTHING, EMPTY_STRING));
        }
    }

    @Nested
    @DisplayName(TEST_ASK_INTEGER)
    class AskIntegerTests {

        @Test
        @DisplayName(TEST_READS_INTEGER)
        void readsInteger() {
            UtilsIO io = ioWithInput(INT_TEST_INPUT + NEWLINE);
            assertEquals(INT_TEST_INPUT, io.askForInteger(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR));
        }

        @Test
        @DisplayName(TEST_RETRIES_NON_INTEGER)
        void retriesOnNonInteger() {
            UtilsIO io = ioWithInput(MESSAGE_TEST_INPUT + NEWLINE + INT_TEST_INPUT + NEWLINE);
            assertEquals(INT_TEST_INPUT, io.askForInteger(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR));

            UtilsIO ioFloat = ioWithInput(FLOAT_TEST_INPUT + NEWLINE + INT_TEST_INPUT + NEWLINE);
            assertEquals(INT_TEST_INPUT, ioFloat.askForInteger(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR));
        }

        @Test
        @DisplayName(TEST_USES_DEFAULTS_MESSAGES)
        void integerMessagesNullOrEmpty() {
            assertEquals(INT_TEST_INPUT, ioWithInput(INT_TEST_INPUT + NEWLINE).askForInteger(null, MESSAGE_ERROR));
            assertEquals(INT_TEST_INPUT, ioWithInput(INT_TEST_INPUT + NEWLINE).askForInteger(EMPTY_STRING, MESSAGE_ERROR));
            assertEquals(INT_TEST_INPUT, ioWithInput(INT_TEST_INPUT + NEWLINE).askForInteger(MESSAGE_ASK_ANYTHING, null));
            assertEquals(INT_TEST_INPUT, ioWithInput(INT_TEST_INPUT + NEWLINE).askForInteger(MESSAGE_ASK_ANYTHING, EMPTY_STRING));
        }
    }

    @Nested
    @DisplayName(TEST_ASK_FLOAT)
    class AskFloatTests {

        @Test
        @DisplayName(TEST_READS_FLOAT)
        void readsFloat() {
            UtilsIO io = ioWithInput(FLOAT_TEST_INPUT + NEWLINE);
            assertEquals(FLOAT_TEST_INPUT, io.askForFloat(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR));
        }

        @Test
        @DisplayName(TEST_RETRIES_NON_FLOAT)
        void retriesOnNonFloat() {
            UtilsIO io = ioWithInput(MESSAGE_TEST_INPUT + NEWLINE + FLOAT_TEST_INPUT + NEWLINE);
            assertEquals(FLOAT_TEST_INPUT, io.askForFloat(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR));
        }

        @Test
        @DisplayName(TEST_ACCEPTS_INTEGER_AS_FLOAT)
        void acceptsIntegerAsFloat() {
            UtilsIO io = ioWithInput(INT_TEST_INPUT + NEWLINE);
            assertEquals((float) INT_TEST_INPUT, io.askForFloat(MESSAGE_ASK_ANYTHING, MESSAGE_ERROR));
        }

        @Test
        @DisplayName(TEST_USES_DEFAULTS_MESSAGES)
        void floatMessagesNullOrEmpty() {
            assertEquals(FLOAT_TEST_INPUT, ioWithInput(FLOAT_TEST_INPUT + NEWLINE).askForFloat(null, MESSAGE_ERROR));
            assertEquals(FLOAT_TEST_INPUT, ioWithInput(FLOAT_TEST_INPUT + NEWLINE).askForFloat(EMPTY_STRING, MESSAGE_ERROR));

            assertEquals(FLOAT_TEST_INPUT, ioWithInput(MESSAGE_TEST_INPUT + NEWLINE + FLOAT_TEST_INPUT + NEWLINE).askForFloat(MESSAGE_ASK_ANYTHING, null));
            assertEquals(FLOAT_TEST_INPUT, ioWithInput(MESSAGE_TEST_INPUT + NEWLINE + FLOAT_TEST_INPUT + NEWLINE).askForFloat(MESSAGE_ASK_ANYTHING, EMPTY_STRING));
        }
    }

    @Nested
    @DisplayName(TEST_SHOW_BETS)
    class ShowBetsTests {

        @Test
        @DisplayName(TEST_THROWS_NULL_EMPTY)
        void throwsOnNullOrEmpty() {
            UtilsIO io = new UtilsIO();
            assertThrows(IllegalArgumentException.class, () -> io.showBets(null));
            assertThrows(IllegalArgumentException.class, () -> io.showBets(EMPTY_STRING));
        }

        @Test
        @DisplayName(TEST_IGNORES_MALFORMED)
        void ignoresMalformedRows() {
            UtilsIO io = new UtilsIO();
            assertDoesNotThrow(() -> io.showBets(RANDOM_STRING));
            assertDoesNotThrow(() -> io.showBets(MALFORMED_DATA_1));
            assertDoesNotThrow(() -> io.showBets(MALFORMED_DATA_2));
        }

        @Test
        @DisplayName(TEST_PRINTS_VALID_ROWS)
        void printsValidRows() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            UtilsIO io = new UtilsIO();
            String validData = SPORT_SOCCER + "," + EVENT_CHAMPIONSHIP_FINAL + "," + DATE_202312011200 + "," + BETTOR_JOHN_DOE + "," + BET_HOME_WIN + "," + ODDS_1_8 + "," + AMOUNT_75_5 + NEWLINE
                    + SPORT_BASKETBALL + "," + EVENT_LEAGUE_MATCH + "," + DATE_202312021300 + "," + BETTOR_JANE_SMITH + "," + BET_OVER_150 + ",2.1," + AMOUNT_65_2;
            io.showBets(validData);

            String text = output.toString();
            assertTrue(text.contains(BETTOR_JOHN));
            assertTrue(text.contains(BETTOR_JANE));
            assertTrue(text.contains(ODDS_1_8));
            assertTrue(text.contains(AMOUNT_75_50));
        }

        @Test
        @DisplayName(TEST_SKIPS_BAD_ODDS)
        void skipsBadOdds() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            UtilsIO io = new UtilsIO();
            String mixedData = SPORT_SOCCER + "," + EVENT_ONE + "," + DATE_202312011200 + "," + BETTOR_JOHN_DOE + "," + BET_WIN + "," + INVALID_ODDS + "," + AMOUNT_75_5 + NEWLINE
                    + SPORT_BASKETBALL + "," + EVENT_TWO + "," + DATE_202312021200 + "," + BETTOR_JANE_SMITH + "," + BET_WIN + "," + ODDS_2_0 + "," + AMOUNT_65_2;
            io.showBets(mixedData);

            String text = output.toString();
            assertTrue(text.contains(BETTOR_JANE));
            assertFalse(text.contains(BETTOR_JOHN));
        }

        @Test
        @DisplayName(TEST_SKIPS_BAD_AMOUNT)
        void skipsBadAmount() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            UtilsIO io = new UtilsIO();
            String mixedData = SPORT_SOCCER + "," + EVENT_ONE + "," + DATE_202312011200 + "," + BETTOR_JOHN_DOE + "," + BET_WIN + "," + ODDS_1_8 + "," + INVALID_AMOUNT + NEWLINE
                    + SPORT_BASKETBALL + "," + EVENT_TWO + "," + DATE_202312021200 + "," + BETTOR_JANE_SMITH + "," + BET_WIN + "," + ODDS_2_0 + "," + AMOUNT_65_2;
            io.showBets(mixedData);

            String text = output.toString();
            assertTrue(text.contains(BETTOR_JANE));
            assertFalse(text.contains(BETTOR_JOHN));
        }

        @Test
        @DisplayName(TEST_SHOWS_VALID_MIXED)
        void showsOnlyValidMixed() {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            UtilsIO io = new UtilsIO();
            String mixedData = SPORT_SOCCER + "," + EVENT_ONE + "," + DATE_202312011200 + "," + BETTOR_JOHN_DOE + "," + BET_WIN + "," + ODDS_1_8 + "," + AMOUNT_75_5 + NEWLINE +
                INVALID_DATA + NEWLINE +
                SPORT_BASKETBALL + "," + EVENT_TWO + "," + DATE_202312021200 + "," + BETTOR_JANE_SMITH + "," + BET_WIN + "," + ODDS_2_0 + "," + AMOUNT_65_2 + NEWLINE +
                SPORT_TENNIS + "," + EVENT_THREE + "," + DATE_202312031200 + "," + BETTOR_BOB_JOHNSON + "," + BET_UNDER + "," + INVALID_ODDS + "," + AMOUNT_80_0 + NEWLINE +
                SPORT_BASEBALL + "," + EVENT_FOUR + "," + DATE_202312041200 + "," + BETTOR_ALICE_BROWN + "," + BET_WIN + "," + ODDS_2_5 + "," + AMOUNT_60_5;
            io.showBets(mixedData);

            String text = output.toString();
            assertTrue(text.contains(BETTOR_JOHN));
            assertTrue(text.contains(BETTOR_JANE));
            assertTrue(text.contains(BETTOR_ALICE));
            assertFalse(text.contains(BETTOR_BOB));
        }
    }
}