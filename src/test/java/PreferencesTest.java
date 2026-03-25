import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.Preferences;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for Preferences class")
public class PreferencesTest {

    // Test message constants
    private static final String CONSTRUCTOR_TESTS = "Constructor Tests";
    private static final String GETTER_TESTS = "Getter Tests";
    private static final String SETTER_TESTS = "Setter Tests";
    private static final String VALIDATION_TESTS = "Validation Tests";
    private static final String EDGE_CASE_TESTS = "Edge Case Tests";

    // Default values
    private static final double DEFAULT_MIN_ODD = 1.01;
    private static final double DEFAULT_MAX_AMOUNT = 500.0;

    // Test values
    private static final double VALID_MIN_ODD = 2.5;
    private static final double VALID_MIN_ODD_2 = 1.5;
    private static final double VALID_MIN_ODD_3 = 3.0;
    private static final double VALID_MAX_AMOUNT = 1000.0;
    private static final double VALID_MAX_AMOUNT_2 = 750.0;
    private static final double VALID_MAX_AMOUNT_3 = 250.0;

    // Invalid values
    private static final double INVALID_MIN_ODD_EQUAL = 1.0;
    private static final double INVALID_MIN_ODD_LESS = 0.5;
    private static final double INVALID_MIN_ODD_NEGATIVE = -2.0;
    private static final double INVALID_MAX_AMOUNT_ZERO = 0.0;
    private static final double INVALID_MAX_AMOUNT_NEGATIVE = -100.0;

    @Nested
    @DisplayName(CONSTRUCTOR_TESTS)
    class ConstructorTests {

        @Test
        @DisplayName("Should create Preferences with default values")
        void shouldCreatePreferencesWithDefaultValues() {
            Preferences prefs = new Preferences();
            assertNotNull(prefs);
            assertEquals(DEFAULT_MIN_ODD, prefs.getMinOdd());
            assertEquals(DEFAULT_MAX_AMOUNT, prefs.getMaxAmount());
            assertTrue(prefs.isNotificationsEnabled());
        }

        @Test
        @DisplayName("Should initialize minOdd to 1.01")
        void shouldInitializeMinOddToOnePointZeroOne() {
            Preferences prefs = new Preferences();
            assertEquals(1.01, prefs.getMinOdd());
        }

        @Test
        @DisplayName("Should initialize maxAmount to 500.0")
        void shouldInitializeMaxAmountToFiveHundred() {
            Preferences prefs = new Preferences();
            assertEquals(500.0, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should initialize notificationsEnabled to true")
        void shouldInitializeNotificationsEnabledToTrue() {
            Preferences prefs = new Preferences();
            assertTrue(prefs.isNotificationsEnabled());
        }
    }

    @Nested
    @DisplayName(GETTER_TESTS)
    class GetterTests {

        @Test
        @DisplayName("Should return correct minOdd value")
        void shouldReturnCorrectMinOddValue() {
            Preferences prefs = new Preferences();
            double minOdd = prefs.getMinOdd();
            assertEquals(DEFAULT_MIN_ODD, minOdd);
        }

        @Test
        @DisplayName("Should return correct maxAmount value")
        void shouldReturnCorrectMaxAmountValue() {
            Preferences prefs = new Preferences();
            double maxAmount = prefs.getMaxAmount();
            assertEquals(DEFAULT_MAX_AMOUNT, maxAmount);
        }

        @Test
        @DisplayName("Should return correct notificationsEnabled value")
        void shouldReturnCorrectNotificationsEnabledValue() {
            Preferences prefs = new Preferences();
            boolean notificationsEnabled = prefs.isNotificationsEnabled();
            assertTrue(notificationsEnabled);
        }

        @Test
        @DisplayName("Should return updated minOdd after setter")
        void shouldReturnUpdatedMinOddAfterSetter() {
            Preferences prefs = new Preferences();
            prefs.setMinOdd(VALID_MIN_ODD);
            assertEquals(VALID_MIN_ODD, prefs.getMinOdd());
        }

        @Test
        @DisplayName("Should return updated maxAmount after setter")
        void shouldReturnUpdatedMaxAmountAfterSetter() {
            Preferences prefs = new Preferences();
            prefs.setMaxAmount(VALID_MAX_AMOUNT);
            assertEquals(VALID_MAX_AMOUNT, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should return updated notificationsEnabled after setter")
        void shouldReturnUpdatedNotificationsEnabledAfterSetter() {
            Preferences prefs = new Preferences();
            prefs.setNotificationsEnabled(false);
            assertFalse(prefs.isNotificationsEnabled());
        }
    }

    @Nested
    @DisplayName(SETTER_TESTS)
    class SetterTests {

        @Test
        @DisplayName("Should set valid minOdd successfully")
        void shouldSetValidMinOddSuccessfully() {
            Preferences prefs = new Preferences();
            assertDoesNotThrow(() -> prefs.setMinOdd(VALID_MIN_ODD));
            assertEquals(VALID_MIN_ODD, prefs.getMinOdd());
        }

        @Test
        @DisplayName("Should set another valid minOdd successfully")
        void shouldSetAnotherValidMinOddSuccessfully() {
            Preferences prefs = new Preferences();
            prefs.setMinOdd(VALID_MIN_ODD_2);
            assertEquals(VALID_MIN_ODD_2, prefs.getMinOdd());
        }

        @Test
        @DisplayName("Should set third valid minOdd successfully")
        void shouldSetThirdValidMinOddSuccessfully() {
            Preferences prefs = new Preferences();
            prefs.setMinOdd(VALID_MIN_ODD_3);
            assertEquals(VALID_MIN_ODD_3, prefs.getMinOdd());
        }

        @Test
        @DisplayName("Should set valid maxAmount successfully")
        void shouldSetValidMaxAmountSuccessfully() {
            Preferences prefs = new Preferences();
            assertDoesNotThrow(() -> prefs.setMaxAmount(VALID_MAX_AMOUNT));
            assertEquals(VALID_MAX_AMOUNT, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should set another valid maxAmount successfully")
        void shouldSetAnotherValidMaxAmountSuccessfully() {
            Preferences prefs = new Preferences();
            prefs.setMaxAmount(VALID_MAX_AMOUNT_2);
            assertEquals(VALID_MAX_AMOUNT_2, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should set third valid maxAmount successfully")
        void shouldSetThirdValidMaxAmountSuccessfully() {
            Preferences prefs = new Preferences();
            prefs.setMaxAmount(VALID_MAX_AMOUNT_3);
            assertEquals(VALID_MAX_AMOUNT_3, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should enable notifications successfully")
        void shouldEnableNotificationsSuccessfully() {
            Preferences prefs = new Preferences();
            prefs.setNotificationsEnabled(true);
            assertTrue(prefs.isNotificationsEnabled());
        }

        @Test
        @DisplayName("Should disable notifications successfully")
        void shouldDisableNotificationsSuccessfully() {
            Preferences prefs = new Preferences();
            prefs.setNotificationsEnabled(false);
            assertFalse(prefs.isNotificationsEnabled());
        }

        @Test
        @DisplayName("Should toggle notifications from enabled to disabled")
        void shouldToggleNotificationsFromEnabledToDisabled() {
            Preferences prefs = new Preferences();
            assertTrue(prefs.isNotificationsEnabled());
            prefs.setNotificationsEnabled(false);
            assertFalse(prefs.isNotificationsEnabled());
        }

        @Test
        @DisplayName("Should toggle notifications from disabled to enabled")
        void shouldToggleNotificationsFromDisabledToEnabled() {
            Preferences prefs = new Preferences();
            prefs.setNotificationsEnabled(false);
            assertFalse(prefs.isNotificationsEnabled());
            prefs.setNotificationsEnabled(true);
            assertTrue(prefs.isNotificationsEnabled());
        }

        @Test
        @DisplayName("Should allow minOdd slightly above 1.0")
        void shouldAllowMinOddSlightlyAboveOne() {
            Preferences prefs = new Preferences();
            double slightlyAboveOne = 1.001;
            assertDoesNotThrow(() -> prefs.setMinOdd(slightlyAboveOne));
            assertEquals(slightlyAboveOne, prefs.getMinOdd());
        }

        @Test
        @DisplayName("Should allow very large minOdd values")
        void shouldAllowVeryLargeMinOddValues() {
            Preferences prefs = new Preferences();
            double largeOdd = 999999.99;
            assertDoesNotThrow(() -> prefs.setMinOdd(largeOdd));
            assertEquals(largeOdd, prefs.getMinOdd());
        }

        @Test
        @DisplayName("Should allow very small maxAmount values")
        void shouldAllowVerySmallMaxAmountValues() {
            Preferences prefs = new Preferences();
            double smallAmount = 0.01;
            assertDoesNotThrow(() -> prefs.setMaxAmount(smallAmount));
            assertEquals(smallAmount, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should allow very large maxAmount values")
        void shouldAllowVeryLargeMaxAmountValues() {
            Preferences prefs = new Preferences();
            double largeAmount = 1000000.0;
            assertDoesNotThrow(() -> prefs.setMaxAmount(largeAmount));
            assertEquals(largeAmount, prefs.getMaxAmount());
        }
    }

    @Nested
    @DisplayName(VALIDATION_TESTS)
    class ValidationTests {

        @Test
        @DisplayName("Should throw exception for minOdd equal to 1.0")
        void shouldThrowExceptionForMinOddEqualToOne() {
            Preferences prefs = new Preferences();
            assertThrows(IllegalArgumentException.class, () -> prefs.setMinOdd(INVALID_MIN_ODD_EQUAL));
        }

        @Test
        @DisplayName("Should throw exception for minOdd less than 1.0")
        void shouldThrowExceptionForMinOddLessThanOne() {
            Preferences prefs = new Preferences();
            assertThrows(IllegalArgumentException.class, () -> prefs.setMinOdd(INVALID_MIN_ODD_LESS));
        }

        @Test
        @DisplayName("Should throw exception for negative minOdd")
        void shouldThrowExceptionForNegativeMinOdd() {
            Preferences prefs = new Preferences();
            assertThrows(IllegalArgumentException.class, () -> prefs.setMinOdd(INVALID_MIN_ODD_NEGATIVE));
        }

        @Test
        @DisplayName("Should throw exception for maxAmount equal to zero")
        void shouldThrowExceptionForMaxAmountEqualToZero() {
            Preferences prefs = new Preferences();
            assertThrows(IllegalArgumentException.class, () -> prefs.setMaxAmount(INVALID_MAX_AMOUNT_ZERO));
        }

        @Test
        @DisplayName("Should throw exception for negative maxAmount")
        void shouldThrowExceptionForNegativeMaxAmount() {
            Preferences prefs = new Preferences();
            assertThrows(IllegalArgumentException.class, () -> prefs.setMaxAmount(INVALID_MAX_AMOUNT_NEGATIVE));
        }

        @Test
        @DisplayName("Should preserve original minOdd after failed validation")
        void shouldPreserveOriginalMinOddAfterFailedValidation() {
            Preferences prefs = new Preferences();
            prefs.setMinOdd(VALID_MIN_ODD);
            assertThrows(IllegalArgumentException.class, () -> prefs.setMinOdd(INVALID_MIN_ODD_EQUAL));
            assertEquals(VALID_MIN_ODD, prefs.getMinOdd());
        }

        @Test
        @DisplayName("Should preserve original maxAmount after failed validation")
        void shouldPreserveOriginalMaxAmountAfterFailedValidation() {
            Preferences prefs = new Preferences();
            prefs.setMaxAmount(VALID_MAX_AMOUNT);
            assertThrows(IllegalArgumentException.class, () -> prefs.setMaxAmount(INVALID_MAX_AMOUNT_ZERO));
            assertEquals(VALID_MAX_AMOUNT, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should validate minOdd boundary at 1.0")
        void shouldValidateMinOddBoundaryAtOne() {
            Preferences prefs = new Preferences();
            // Exactly 1.0 should fail
            assertThrows(IllegalArgumentException.class, () -> prefs.setMinOdd(1.0));
            // Just above 1.0 should succeed
            assertDoesNotThrow(() -> prefs.setMinOdd(1.0000001));
        }

        @Test
        @DisplayName("Should validate maxAmount boundary at zero")
        void shouldValidateMaxAmountBoundaryAtZero() {
            Preferences prefs = new Preferences();
            // Exactly 0.0 should fail
            assertThrows(IllegalArgumentException.class, () -> prefs.setMaxAmount(0.0));
            // Just above 0.0 should succeed
            assertDoesNotThrow(() -> prefs.setMaxAmount(0.0000001));
        }
    }

    @Nested
    @DisplayName(EDGE_CASE_TESTS)
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle multiple instances independently")
        void shouldHandleMultipleInstancesIndependently() {
            Preferences prefs1 = new Preferences();
            Preferences prefs2 = new Preferences();

            prefs1.setMinOdd(VALID_MIN_ODD);
            prefs1.setMaxAmount(VALID_MAX_AMOUNT);
            prefs1.setNotificationsEnabled(false);

            prefs2.setMinOdd(VALID_MIN_ODD_2);
            prefs2.setMaxAmount(VALID_MAX_AMOUNT_2);
            prefs2.setNotificationsEnabled(true);

            assertEquals(VALID_MIN_ODD, prefs1.getMinOdd());
            assertEquals(VALID_MAX_AMOUNT, prefs1.getMaxAmount());
            assertFalse(prefs1.isNotificationsEnabled());

            assertEquals(VALID_MIN_ODD_2, prefs2.getMinOdd());
            assertEquals(VALID_MAX_AMOUNT_2, prefs2.getMaxAmount());
            assertTrue(prefs2.isNotificationsEnabled());
        }

        @Test
        @DisplayName("Should allow independent modification of all fields")
        void shouldAllowIndependentModificationOfAllFields() {
            Preferences prefs = new Preferences();

            prefs.setMinOdd(VALID_MIN_ODD);
            assertEquals(VALID_MIN_ODD, prefs.getMinOdd());
            assertEquals(DEFAULT_MAX_AMOUNT, prefs.getMaxAmount());
            assertTrue(prefs.isNotificationsEnabled());

            prefs.setMaxAmount(VALID_MAX_AMOUNT);
            assertEquals(VALID_MIN_ODD, prefs.getMinOdd());
            assertEquals(VALID_MAX_AMOUNT, prefs.getMaxAmount());
            assertTrue(prefs.isNotificationsEnabled());

            prefs.setNotificationsEnabled(false);
            assertEquals(VALID_MIN_ODD, prefs.getMinOdd());
            assertEquals(VALID_MAX_AMOUNT, prefs.getMaxAmount());
            assertFalse(prefs.isNotificationsEnabled());
        }

        @Test
        @DisplayName("Should maintain consistency across multiple setter calls")
        void shouldMaintainConsistencyAcrossMultipleSetterCalls() {
            Preferences prefs = new Preferences();

            for (int i = 0; i < 5; i++) {
                double newOdd = 1.5 + i;
                double newAmount = 100.0 + (i * 50);
                prefs.setMinOdd(newOdd);
                prefs.setMaxAmount(newAmount);

                assertEquals(newOdd, prefs.getMinOdd());
                assertEquals(newAmount, prefs.getMaxAmount());
            }
        }

        @Test
        @DisplayName("Should handle rapid notification toggle")
        void shouldHandleRapidNotificationToggle() {
            Preferences prefs = new Preferences();

            for (int i = 0; i < 10; i++) {
                boolean toggle = (i % 2) == 0;
                prefs.setNotificationsEnabled(toggle);
                assertEquals(toggle, prefs.isNotificationsEnabled());
            }
        }

        @Test
        @DisplayName("Should not affect one field when modifying another")
        void shouldNotAffectOneFieldWhenModifyingAnother() {
            Preferences prefs = new Preferences();
            prefs.setMinOdd(VALID_MIN_ODD);
            prefs.setMaxAmount(VALID_MAX_AMOUNT);
            prefs.setNotificationsEnabled(false);

            // Modify minOdd and verify others unchanged
            prefs.setMinOdd(VALID_MIN_ODD_2);
            assertEquals(VALID_MAX_AMOUNT, prefs.getMaxAmount());
            assertFalse(prefs.isNotificationsEnabled());

            // Modify maxAmount and verify others unchanged
            prefs.setMaxAmount(VALID_MAX_AMOUNT_2);
            assertEquals(VALID_MIN_ODD_2, prefs.getMinOdd());
            assertFalse(prefs.isNotificationsEnabled());

            // Modify notifications and verify others unchanged
            prefs.setNotificationsEnabled(true);
            assertEquals(VALID_MIN_ODD_2, prefs.getMinOdd());
            assertEquals(VALID_MAX_AMOUNT_2, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should handle extreme but valid double values")
        void shouldHandleExtremeButValidDoubleValues() {
            Preferences prefs = new Preferences();

            // Very large odd value
            prefs.setMinOdd(1e10);
            assertEquals(1e10, prefs.getMinOdd());

            // Very small but positive amount
            prefs.setMaxAmount(1e-5);
            assertEquals(1e-5, prefs.getMaxAmount());
        }

        @Test
        @DisplayName("Should validate that minOdd must be strictly greater than 1.0")
        void shouldValidateThatMinOddMustBeStrictlyGreaterThanOne() {
            Preferences prefs = new Preferences();

            // Test boundary values
            assertThrows(IllegalArgumentException.class, () -> prefs.setMinOdd(1.0));
            assertThrows(IllegalArgumentException.class, () -> prefs.setMinOdd(0.99));
            assertDoesNotThrow(() -> prefs.setMinOdd(1.01));
            assertDoesNotThrow(() -> prefs.setMinOdd(1.001));
        }

        @Test
        @DisplayName("Should validate that maxAmount must be strictly positive")
        void shouldValidateThatMaxAmountMustBeStrictlyPositive() {
            Preferences prefs = new Preferences();

            // Test boundary values
            assertThrows(IllegalArgumentException.class, () -> prefs.setMaxAmount(0.0));
            assertThrows(IllegalArgumentException.class, () -> prefs.setMaxAmount(-0.01));
            assertDoesNotThrow(() -> prefs.setMaxAmount(0.01));
            assertDoesNotThrow(() -> prefs.setMaxAmount(0.001));
        }

        @Test
        @DisplayName("Should preserve default values if setters are never called")
        void shouldPreserveDefaultValuesIfSettersAreNeverCalled() {
            Preferences prefs = new Preferences();

            assertEquals(DEFAULT_MIN_ODD, prefs.getMinOdd());
            assertEquals(DEFAULT_MAX_AMOUNT, prefs.getMaxAmount());
            assertTrue(prefs.isNotificationsEnabled());
        }

        @Test
        @DisplayName("Should reset to new values after modification")
        void shouldResetToNewValuesAfterModification() {
            Preferences prefs = new Preferences();

            // Set new values
            prefs.setMinOdd(VALID_MIN_ODD);
            prefs.setMaxAmount(VALID_MAX_AMOUNT);
            prefs.setNotificationsEnabled(false);

            // Reset to different values
            prefs.setMinOdd(VALID_MIN_ODD_3);
            prefs.setMaxAmount(VALID_MAX_AMOUNT_3);
            prefs.setNotificationsEnabled(true);

            assertEquals(VALID_MIN_ODD_3, prefs.getMinOdd());
            assertEquals(VALID_MAX_AMOUNT_3, prefs.getMaxAmount());
            assertTrue(prefs.isNotificationsEnabled());
        }
    }

    @Nested
    @DisplayName("toString() Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return non-null string from toString()")
        void shouldReturnNonNullString() {
            Preferences prefs = new Preferences();
            String result = prefs.toString();
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should include minOdd in toString()")
        void shouldIncludeMinOddInToString() {
            Preferences prefs = new Preferences();
            String result = prefs.toString();
            assertTrue(result.contains("1.01"));
        }

        @Test
        @DisplayName("Should include maxAmount in toString()")
        void shouldIncludeMaxAmountInToString() {
            Preferences prefs = new Preferences();
            String result = prefs.toString();
            assertTrue(result.contains("500.00"));
        }

        @Test
        @DisplayName("Should include notificationsEnabled in toString()")
        void shouldIncludeNotificationsEnabledInToString() {
            Preferences prefs = new Preferences();
            String result = prefs.toString();
            assertTrue(result.contains("true"));
        }

        @Test
        @DisplayName("Should format with comma separator")
        void shouldFormatWithCommaSeparator() {
            Preferences prefs = new Preferences();
            String result = prefs.toString();
            String[] parts = result.split(",");
            assertEquals(3, parts.length);
        }

        @Test
        @DisplayName("Should format minOdd with 2 decimal places")
        void shouldFormatMinOddWithTwoDecimals() {
            Preferences prefs = new Preferences();
            prefs.setMinOdd(2.5);
            String result = prefs.toString();
            assertTrue(result.contains("2.50"));
        }

        @Test
        @DisplayName("Should format maxAmount with 2 decimal places")
        void shouldFormatMaxAmountWithTwoDecimals() {
            Preferences prefs = new Preferences();
            prefs.setMaxAmount(1000.0);
            String result = prefs.toString();
            assertTrue(result.contains("1000.00"));
        }

        @Test
        @DisplayName("Should show correct format for default values")
        void shouldShowCorrectFormatForDefaultValues() {
            Preferences prefs = new Preferences();
            String result = prefs.toString();
            assertEquals("1.01,500.00,true", result);
        }

        @Test
        @DisplayName("Should reflect updated minOdd in toString()")
        void shouldReflectUpdatedMinOdd() {
            Preferences prefs = new Preferences();
            prefs.setMinOdd(VALID_MIN_ODD);
            String result = prefs.toString();
            assertTrue(result.contains("2.50"));
        }

        @Test
        @DisplayName("Should reflect updated maxAmount in toString()")
        void shouldReflectUpdatedMaxAmount() {
            Preferences prefs = new Preferences();
            prefs.setMaxAmount(VALID_MAX_AMOUNT);
            String result = prefs.toString();
            assertTrue(result.contains("1000.00"));
        }

        @Test
        @DisplayName("Should reflect disabled notifications in toString()")
        void shouldReflectDisabledNotifications() {
            Preferences prefs = new Preferences();
            prefs.setNotificationsEnabled(false);
            String result = prefs.toString();
            assertTrue(result.contains("false"));
        }

        @Test
        @DisplayName("Should format boolean as lowercase string")
        void shouldFormatBooleanAsLowercaseString() {
            Preferences prefs = new Preferences();
            prefs.setNotificationsEnabled(true);
            String result = prefs.toString();
            assertTrue(result.contains("true"));
            assertFalse(result.contains("True"));
            assertFalse(result.contains("TRUE"));
        }

        @Test
        @DisplayName("Should update toString() after multiple changes")
        void shouldUpdateToStringAfterMultipleChanges() {
            Preferences prefs = new Preferences();
            String initial = prefs.toString();
            
            prefs.setMinOdd(VALID_MIN_ODD_2);
            prefs.setMaxAmount(VALID_MAX_AMOUNT_2);
            prefs.setNotificationsEnabled(false);
            
            String updated = prefs.toString();
            assertNotEquals(initial, updated);
            assertTrue(updated.contains("1.50"));
            assertTrue(updated.contains("750.00"));
            assertTrue(updated.contains("false"));
        }
    }
}
