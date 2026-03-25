import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.UserStats;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for UserStats class")
public class UserStatsTest {

    // Test message constants
    private static final String CONSTRUCTOR_TESTS = "Constructor Tests";
    private static final String GETTER_TESTS = "Getter Tests";
    private static final String REGISTER_BET_TESTS = "Register Bet Tests";
    private static final String AVERAGE_AMOUNT_TESTS = "Average Amount Tests";
    private static final String VALIDATION_TESTS = "Validation Tests";
    private static final String EDGE_CASE_TESTS = "Edge Case Tests";

    // Default values
    private static final int DEFAULT_TOTAL_BETS = 0;
    private static final double DEFAULT_TOTAL_AMOUNT = 0.0;

    // Test values
    private static final double VALID_BET_AMOUNT = 50.0;
    private static final double VALID_BET_AMOUNT_2 = 100.0;
    private static final double VALID_BET_AMOUNT_3 = 25.0;
    private static final double VALID_BET_AMOUNT_4 = 75.0;
    private static final double VALID_BET_AMOUNT_5 = 150.0;

    // Invalid values
    private static final double INVALID_BET_ZERO = 0.0;
    private static final double INVALID_BET_NEGATIVE = -50.0;
    private static final double INVALID_BET_VERY_NEGATIVE = -1000.0;

    // Extreme values
    private static final double VERY_SMALL_BET = 0.01;
    private static final double VERY_LARGE_BET = 1000000.0;

    @Nested
    @DisplayName(CONSTRUCTOR_TESTS)
    class ConstructorTests {

        @Test
        @DisplayName("Should create UserStats with default values")
        void shouldCreateUserStatsWithDefaultValues() {
            UserStats stats = new UserStats();
            assertNotNull(stats);
            assertEquals(DEFAULT_TOTAL_BETS, stats.getTotalBets());
            assertEquals(DEFAULT_TOTAL_AMOUNT, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should initialize totalBets to 0")
        void shouldInitializeTotalBetsToZero() {
            UserStats stats = new UserStats();
            assertEquals(0, stats.getTotalBets());
        }

        @Test
        @DisplayName("Should initialize totalAmount to 0.0")
        void shouldInitializeTotalAmountToZero() {
            UserStats stats = new UserStats();
            assertEquals(0.0, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should initialize average amount to 0.0")
        void shouldInitializeAverageAmountToZero() {
            UserStats stats = new UserStats();
            assertEquals(0.0, stats.getAverageAmount());
        }
    }

    @Nested
    @DisplayName(GETTER_TESTS)
    class GetterTests {

        @Test
        @DisplayName("Should return correct totalBets")
        void shouldReturnCorrectTotalBets() {
            UserStats stats = new UserStats();
            assertEquals(0, stats.getTotalBets());
        }

        @Test
        @DisplayName("Should return correct totalAmount")
        void shouldReturnCorrectTotalAmount() {
            UserStats stats = new UserStats();
            assertEquals(0.0, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should return correct average amount when no bets")
        void shouldReturnCorrectAverageAmountWhenNoBets() {
            UserStats stats = new UserStats();
            assertEquals(0.0, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should return updated totalBets after registration")
        void shouldReturnUpdatedTotalBetsAfterRegistration() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            assertEquals(1, stats.getTotalBets());
        }

        @Test
        @DisplayName("Should return updated totalAmount after registration")
        void shouldReturnUpdatedTotalAmountAfterRegistration() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            assertEquals(VALID_BET_AMOUNT, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should return updated average amount after registration")
        void shouldReturnUpdatedAverageAmountAfterRegistration() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            assertEquals(VALID_BET_AMOUNT, stats.getAverageAmount());
        }
    }

    @Nested
    @DisplayName(REGISTER_BET_TESTS)
    class RegisterBetTests {

        @Test
        @DisplayName("Should register a valid bet successfully")
        void shouldRegisterAValidBetSuccessfully() {
            UserStats stats = new UserStats();
            assertDoesNotThrow(() -> stats.registerBet(VALID_BET_AMOUNT));
            assertEquals(1, stats.getTotalBets());
            assertEquals(VALID_BET_AMOUNT, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should register another valid bet successfully")
        void shouldRegisterAnotherValidBetSuccessfully() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            stats.registerBet(VALID_BET_AMOUNT_2);
            assertEquals(2, stats.getTotalBets());
            assertEquals(VALID_BET_AMOUNT + VALID_BET_AMOUNT_2, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should register multiple valid bets successfully")
        void shouldRegisterMultipleValidBetsSuccessfully() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            stats.registerBet(VALID_BET_AMOUNT_2);
            stats.registerBet(VALID_BET_AMOUNT_3);
            stats.registerBet(VALID_BET_AMOUNT_4);
            stats.registerBet(VALID_BET_AMOUNT_5);
            assertEquals(5, stats.getTotalBets());
            assertEquals(VALID_BET_AMOUNT + VALID_BET_AMOUNT_2 + VALID_BET_AMOUNT_3 + VALID_BET_AMOUNT_4 + VALID_BET_AMOUNT_5, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should increment bet counter for each bet")
        void shouldIncrementBetCounterForEachBet() {
            UserStats stats = new UserStats();
            for (int i = 1; i <= 5; i++) {
                stats.registerBet(50.0);
                assertEquals(i, stats.getTotalBets());
            }
        }

        @Test
        @DisplayName("Should accumulate total amount correctly")
        void shouldAccumulateTotalAmountCorrectly() {
            UserStats stats = new UserStats();
            double expectedTotal = 0.0;
            for (int i = 0; i < 5; i++) {
                double amount = 50.0 + (i * 10);
                stats.registerBet(amount);
                expectedTotal += amount;
                assertEquals(expectedTotal, stats.getTotalAmount());
            }
        }

        @Test
        @DisplayName("Should register very small valid bet amount")
        void shouldRegisterVerySmallValidBetAmount() {
            UserStats stats = new UserStats();
            assertDoesNotThrow(() -> stats.registerBet(VERY_SMALL_BET));
            assertEquals(1, stats.getTotalBets());
            assertEquals(VERY_SMALL_BET, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should register very large valid bet amount")
        void shouldRegisterVeryLargeValidBetAmount() {
            UserStats stats = new UserStats();
            assertDoesNotThrow(() -> stats.registerBet(VERY_LARGE_BET));
            assertEquals(1, stats.getTotalBets());
            assertEquals(VERY_LARGE_BET, stats.getTotalAmount());
        }
    }

    @Nested
    @DisplayName(AVERAGE_AMOUNT_TESTS)
    class AverageAmountTests {

        @Test
        @DisplayName("Should return 0.0 average when no bets registered")
        void shouldReturnZeroAverageWhenNoBetsRegistered() {
            UserStats stats = new UserStats();
            assertEquals(0.0, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should calculate average with one bet")
        void shouldCalculateAverageWithOneBet() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            assertEquals(VALID_BET_AMOUNT, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should calculate average with two equal bets")
        void shouldCalculateAverageWithTwoEqualBets() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            stats.registerBet(VALID_BET_AMOUNT);
            assertEquals(VALID_BET_AMOUNT, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should calculate average with two different bets")
        void shouldCalculateAverageWithTwoDifferentBets() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            stats.registerBet(VALID_BET_AMOUNT_2);
            double expectedAverage = (VALID_BET_AMOUNT + VALID_BET_AMOUNT_2) / 2.0;
            assertEquals(expectedAverage, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should calculate average with multiple bets")
        void shouldCalculateAverageWithMultipleBets() {
            UserStats stats = new UserStats();
            double[] amounts = {VALID_BET_AMOUNT, VALID_BET_AMOUNT_2, VALID_BET_AMOUNT_3, VALID_BET_AMOUNT_4, VALID_BET_AMOUNT_5};
            double total = 0.0;
            for (double amount : amounts) {
                stats.registerBet(amount);
                total += amount;
            }
            double expectedAverage = total / amounts.length;
            assertEquals(expectedAverage, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should update average correctly after each registration")
        void shouldUpdateAverageCorrectlyAfterEachRegistration() {
            UserStats stats = new UserStats();
            stats.registerBet(100.0);
            assertEquals(100.0, stats.getAverageAmount());

            stats.registerBet(50.0);
            assertEquals(75.0, stats.getAverageAmount());

            stats.registerBet(125.0);
            assertEquals(91.666666666, stats.getAverageAmount(), 0.0001);
        }

        @Test
        @DisplayName("Should handle average with very small amounts")
        void shouldHandleAverageWithVerySmallAmounts() {
            UserStats stats = new UserStats();
            stats.registerBet(VERY_SMALL_BET);
            stats.registerBet(VERY_SMALL_BET);
            assertEquals(VERY_SMALL_BET, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should handle average with very large amounts")
        void shouldHandleAverageWithVeryLargeAmounts() {
            UserStats stats = new UserStats();
            stats.registerBet(VERY_LARGE_BET);
            stats.registerBet(VERY_LARGE_BET);
            assertEquals(VERY_LARGE_BET, stats.getAverageAmount());
        }
    }

    @Nested
    @DisplayName(VALIDATION_TESTS)
    class ValidationTests {

        @Test
        @DisplayName("Should throw exception for zero bet amount")
        void shouldThrowExceptionForZeroBetAmount() {
            UserStats stats = new UserStats();
            assertThrows(IllegalArgumentException.class, () -> stats.registerBet(INVALID_BET_ZERO));
        }

        @Test
        @DisplayName("Should throw exception for negative bet amount")
        void shouldThrowExceptionForNegativeBetAmount() {
            UserStats stats = new UserStats();
            assertThrows(IllegalArgumentException.class, () -> stats.registerBet(INVALID_BET_NEGATIVE));
        }

        @Test
        @DisplayName("Should throw exception for very negative bet amount")
        void shouldThrowExceptionForVeryNegativeBetAmount() {
            UserStats stats = new UserStats();
            assertThrows(IllegalArgumentException.class, () -> stats.registerBet(INVALID_BET_VERY_NEGATIVE));
        }

        @Test
        @DisplayName("Should not register invalid bet")
        void shouldNotRegisterInvalidBet() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            assertThrows(IllegalArgumentException.class, () -> stats.registerBet(INVALID_BET_ZERO));
            assertEquals(1, stats.getTotalBets());
            assertEquals(VALID_BET_AMOUNT, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should preserve statistics after invalid bet attempt")
        void shouldPreserveStatisticsAfterInvalidBetAttempt() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            stats.registerBet(VALID_BET_AMOUNT_2);
            double totalBeforeAttempt = stats.getTotalAmount();
            int betsBeforeAttempt = stats.getTotalBets();

            assertThrows(IllegalArgumentException.class, () -> stats.registerBet(INVALID_BET_NEGATIVE));

            assertEquals(betsBeforeAttempt, stats.getTotalBets());
            assertEquals(totalBeforeAttempt, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should validate boundary at zero")
        void shouldValidateBoundaryAtZero() {
            UserStats stats = new UserStats();
            // Exactly 0 should fail
            assertThrows(IllegalArgumentException.class, () -> stats.registerBet(0.0));
            // Just above 0 should succeed
            assertDoesNotThrow(() -> stats.registerBet(0.0000001));
        }
    }

    @Nested
    @DisplayName(EDGE_CASE_TESTS)
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle multiple instances independently")
        void shouldHandleMultipleInstancesIndependently() {
            UserStats stats1 = new UserStats();
            UserStats stats2 = new UserStats();

            stats1.registerBet(VALID_BET_AMOUNT);
            stats1.registerBet(VALID_BET_AMOUNT_2);

            stats2.registerBet(VALID_BET_AMOUNT_3);

            assertEquals(2, stats1.getTotalBets());
            assertEquals(VALID_BET_AMOUNT + VALID_BET_AMOUNT_2, stats1.getTotalAmount());

            assertEquals(1, stats2.getTotalBets());
            assertEquals(VALID_BET_AMOUNT_3, stats2.getTotalAmount());
        }

        @Test
        @DisplayName("Should handle large number of bets")
        void shouldHandleLargeNumberOfBets() {
            UserStats stats = new UserStats();
            int numberOfBets = 1000;
            double betAmount = 10.0;
            double expectedTotal = 0.0;

            for (int i = 0; i < numberOfBets; i++) {
                stats.registerBet(betAmount);
                expectedTotal += betAmount;
            }

            assertEquals(numberOfBets, stats.getTotalBets());
            assertEquals(expectedTotal, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should maintain precision with multiple decimal amounts")
        void shouldMaintainPrecisionWithMultipleDecimalAmounts() {
            UserStats stats = new UserStats();
            stats.registerBet(10.50);
            stats.registerBet(20.75);
            stats.registerBet(15.25);

            assertEquals(3, stats.getTotalBets());
            assertEquals(46.50, stats.getTotalAmount(), 0.001);
        }

        @Test
        @DisplayName("Should calculate correct average across multiple bets")
        void shouldCalculateCorrectAverageAcrossMultipleBets() {
            UserStats stats = new UserStats();
            double[] bets = {100.0, 200.0, 300.0, 400.0, 500.0};
            double total = 0.0;

            for (double bet : bets) {
                stats.registerBet(bet);
                total += bet;
            }

            double expectedAverage = total / bets.length;
            assertEquals(expectedAverage, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should handle alternating small and large bets")
        void shouldHandleAlternatingSmallAndLargeBets() {
            UserStats stats = new UserStats();
            stats.registerBet(VERY_SMALL_BET);
            stats.registerBet(VERY_LARGE_BET);
            stats.registerBet(VERY_SMALL_BET);
            stats.registerBet(VERY_LARGE_BET);

            assertEquals(4, stats.getTotalBets());
            double expectedTotal = (VERY_SMALL_BET * 2) + (VERY_LARGE_BET * 2);
            assertEquals(expectedTotal, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should not allow modification of stats through getters")
        void shouldNotAllowModificationOfStatsThroughGetters() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);

            // Original stats should be unchanged
            assertEquals(1, stats.getTotalBets());
            assertEquals(VALID_BET_AMOUNT, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should continue to accumulate after multiple operations")
        void shouldContinueToAccumulateAfterMultipleOperations() {
            UserStats stats = new UserStats();

            // First round
            stats.registerBet(100.0);
            assertEquals(1, stats.getTotalBets());
            assertEquals(100.0, stats.getTotalAmount());

            // Second round
            stats.registerBet(50.0);
            assertEquals(2, stats.getTotalBets());
            assertEquals(150.0, stats.getTotalAmount());

            // Third round
            stats.registerBet(75.0);
            assertEquals(3, stats.getTotalBets());
            assertEquals(225.0, stats.getTotalAmount());
        }

        @Test
        @DisplayName("Should handle fractions correctly in average calculation")
        void shouldHandleFractionsCorrectlyInAverageCalculation() {
            UserStats stats = new UserStats();
            stats.registerBet(10.0);
            stats.registerBet(20.0);
            stats.registerBet(30.0);

            // Average should be 20.0
            assertEquals(20.0, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should handle average with odd number of bets")
        void shouldHandleAverageWithOddNumberOfBets() {
            UserStats stats = new UserStats();
            stats.registerBet(100.0);
            stats.registerBet(200.0);
            stats.registerBet(300.0);
            stats.registerBet(400.0);
            stats.registerBet(500.0);

            double expectedAverage = 1500.0 / 5.0; // 300.0
            assertEquals(expectedAverage, stats.getAverageAmount());
        }

        @Test
        @DisplayName("Should preserve stats consistency through repeated operations")
        void shouldPreserveStatsConsistencyThroughRepeatedOperations() {
            UserStats stats = new UserStats();

            for (int i = 0; i < 10; i++) {
                stats.registerBet(25.0);
                assertEquals(i + 1, stats.getTotalBets());
                assertEquals((i + 1) * 25.0, stats.getTotalAmount());
                assertEquals(25.0, stats.getAverageAmount());
            }
        }
    }

    @Nested
    @DisplayName("toString() Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return non-null string from toString()")
        void shouldReturnNonNullString() {
            UserStats stats = new UserStats();
            String result = stats.toString();
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should include totalBets in toString()")
        void shouldIncludeTotalBetsInToString() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            String result = stats.toString();
            assertTrue(result.contains("1"));
        }

        @Test
        @DisplayName("Should include totalAmount in toString()")
        void shouldIncludeTotalAmountInToString() {
            UserStats stats = new UserStats();
            stats.registerBet(VALID_BET_AMOUNT);
            String result = stats.toString();
            assertTrue(result.contains("50.00"));
        }

        @Test
        @DisplayName("Should format with comma separator")
        void shouldFormatWithCommaSeparator() {
            UserStats stats = new UserStats();
            String result = stats.toString();
            String[] parts = result.split(",");
            assertEquals(2, parts.length);
        }

        @Test
        @DisplayName("Should format amount with 2 decimal places")
        void shouldFormatAmountWithTwoDecimals() {
            UserStats stats = new UserStats();
            stats.registerBet(33.33);
            String result = stats.toString();
            assertTrue(result.contains("33.33"));
        }

        @Test
        @DisplayName("Should show correct format for default values")
        void shouldShowCorrectFormatForDefaultValues() {
            UserStats stats = new UserStats();
            String result = stats.toString();
            assertEquals("0,0.00", result);
        }

        @Test
        @DisplayName("Should update toString() after registering bet")
        void shouldUpdateToStringAfterRegisteringBet() {
            UserStats stats = new UserStats();
            String beforeResult = stats.toString();
            stats.registerBet(VALID_BET_AMOUNT);
            String afterResult = stats.toString();
            assertNotEquals(beforeResult, afterResult);
        }

        @Test
        @DisplayName("Should format multiple bets correctly")
        void shouldFormatMultipleBetsCorrectly() {
            UserStats stats = new UserStats();
            stats.registerBet(50.0);
            stats.registerBet(100.0);
            stats.registerBet(25.0);
            String result = stats.toString();
            assertTrue(result.contains("3"));
            assertTrue(result.contains("175.00"));
        }

        @Test
        @DisplayName("Should handle large numbers in toString()")
        void shouldHandleLargeNumbers() {
            UserStats stats = new UserStats(1000, 500000.50);
            String result = stats.toString();
            assertTrue(result.contains("1000"));
            assertTrue(result.contains("500000.50"));
        }

        @Test
        @DisplayName("Should format small amounts correctly")
        void shouldFormatSmallAmountsCorrectly() {
            UserStats stats = new UserStats();
            stats.registerBet(0.01);
            String result = stats.toString();
            assertTrue(result.contains("0.01"));
        }
    }
}
