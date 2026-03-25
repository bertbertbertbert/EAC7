import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.User;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for the abstract User class.
 * Uses a concrete test implementation (TestUser) to test all User functionality.
 */
@DisplayName("Tests for abstract User class")
public class UserTest {

    // Test message constants
    private static final String CONSTRUCTOR_TESTS = "Constructor Tests";
    private static final String GETTER_TESTS = "Getter Tests";
    private static final String SETTER_TESTS = "Setter Tests";

    // Test data constants
    private static final String VALID_DNI = "12345678Z";  // Valid format for TestUser
    private static final String VALID_DNI_2 = "87654321A";  // Another valid DNI
    private static final String NULL_DNI = null;
    private static final String BLANK_DNI = "";
    private static final String WHITESPACE_DNI = "   ";

    private static final String TEST_NAME = "John Doe";
    private static final String TEST_NAME_2 = "Jane Smith";
    private static final String TEST_EMAIL = "john@example.com";
    private static final String TEST_EMAIL_2 = "jane@example.com";
    private static final String TEST_LAST_NAME = "Doe";
    private static final String TEST_LAST_NAME_2 = "Smith";
    private static final String TEST_PHONE_NUMBER = "+34 123 456 789";
    private static final String TEST_PHONE_NUMBER_2 = "+34 987 654 321";

    /**
     * Concrete implementation of the abstract User class for testing purposes.
     * Validates IDs in a simple format: must be exactly 9 characters (8 digits + 1 letter).
     */
    static class TestUser extends User {
        public TestUser(String id, String name, String email, String lastName, String phoneNumber) {
            super(id, name, email, lastName, phoneNumber);
        }

        /**
         * Simple validation: ID must be exactly 9 characters (8 digits + 1 letter).
         */
        @Override
        public boolean hasValidId() {
            if (id == null || id.length() != 9) {
                return false;
            }
            // First 8 characters must be digits
            for (int i = 0; i < 8; i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    return false;
                }
            }
            // Last character must be a letter
            return Character.isLetter(id.charAt(8));
        }
    }

    @Nested
    @DisplayName(CONSTRUCTOR_TESTS)
    class ConstructorTests {

        @Test
        @DisplayName("Should create User with valid ID, name, and email")
        void shouldCreateUserWithValidData() {
            assertDoesNotThrow(() -> {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertNotNull(user);
                assertEquals(VALID_DNI, user.getId());
                assertEquals(TEST_NAME, user.getName());
                assertEquals(TEST_EMAIL, user.getEmail());
            });
        }

        @Test
        @DisplayName("Should normalize ID to uppercase in constructor")
        void shouldNormalizeIdToUppercaseInConstructor() {
            TestUser user = new TestUser("12345678z", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            assertEquals("12345678Z", user.getId());
        }

        @Test
        @DisplayName("Should trim whitespace from ID in constructor")
        void shouldTrimWhitespaceFromIdInConstructor() {
            TestUser user = new TestUser("  12345678Z  ", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            assertEquals("12345678Z", user.getId());
        }

        @Test
        @DisplayName("Should throw exception when ID is null")
        void shouldThrowExceptionForNullId() {
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(NULL_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
        }

        @Test
        @DisplayName("Should throw exception when ID is blank")
        void shouldThrowExceptionForBlankId() {
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(BLANK_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(WHITESPACE_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
        }

        @Test
        @DisplayName("Should throw exception when name is null")
        void shouldThrowExceptionForNullName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(VALID_DNI, null, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
        }

        @Test
        @DisplayName("Should throw exception when email is null")
        void shouldThrowExceptionForNullEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(VALID_DNI, TEST_NAME, null, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
        }

        @Test
        @DisplayName("Should throw exception when name and email are null")
        void shouldThrowExceptionForNullNameAndEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(VALID_DNI, null, null, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
        }

        @Test
        @DisplayName("Should throw exception when name is blank")
        void shouldThrowExceptionForBlankName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(VALID_DNI, "", TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(VALID_DNI, "   ", TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
        }

        @Test
        @DisplayName("Should throw exception when email is blank")
        void shouldThrowExceptionForBlankEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(VALID_DNI, TEST_NAME, "", TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new TestUser(VALID_DNI, TEST_NAME, "   ", TEST_LAST_NAME, TEST_PHONE_NUMBER);
            });
        }
    }

    @Nested
    @DisplayName(GETTER_TESTS)
    class GetterTests {

        @Test
        @DisplayName("Should return correct ID")
        void shouldReturnCorrectId() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            assertEquals(VALID_DNI, user.getId());
        }

        @Test
        @DisplayName("Should return correct name")
        void shouldReturnCorrectName() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            assertEquals(TEST_NAME, user.getName());
        }

        @Test
        @DisplayName("Should return correct email")
        void shouldReturnCorrectEmail() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            assertEquals(TEST_EMAIL, user.getEmail());
        }

        @Test
        @DisplayName("Should return normalized ID in uppercase")
        void shouldReturnNormalizedIdInUppercase() {
            TestUser user = new TestUser("87654321a", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            assertEquals("87654321A", user.getId());
        }
    }

    @Nested
    @DisplayName(SETTER_TESTS)
    class SetterTests {

        @Nested
        @DisplayName("setId() Tests")
        class SetIdTests {

            @Test
            @DisplayName("Should set valid ID successfully")
            void shouldSetValidIdSuccessfully() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                user.setId(VALID_DNI_2);
                assertEquals(VALID_DNI_2, user.getId());
            }

            @Test
            @DisplayName("Should normalize ID to uppercase when setting")
            void shouldNormalizeIdToUppercaseWhenSetting() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                user.setId("87654321a");
                assertEquals("87654321A", user.getId());
            }

            @Test
            @DisplayName("Should trim whitespace from ID when setting")
            void shouldTrimWhitespaceFromIdWhenSetting() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                user.setId("  87654321A  ");
                assertEquals("87654321A", user.getId());
            }

            @Test
            @DisplayName("Should throw exception when setting null ID")
            void shouldThrowExceptionForNullId() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertThrows(IllegalArgumentException.class, () -> user.setId(null));
            }

            @Test
            @DisplayName("Should throw exception when setting blank ID")
            void shouldThrowExceptionForBlankId() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertThrows(IllegalArgumentException.class, () -> user.setId(""));
                assertThrows(IllegalArgumentException.class, () -> user.setId("   "));
            }

        }

        @Nested
        @DisplayName("setName() Tests")
        class SetNameTests {

            @Test
            @DisplayName("Should set valid name successfully")
            void shouldSetValidNameSuccessfully() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                user.setName(TEST_NAME_2);
                assertEquals(TEST_NAME_2, user.getName());
            }

            @Test
            @DisplayName("Should throw exception when setting null name")
            void shouldThrowExceptionForNullName() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertThrows(IllegalArgumentException.class, () -> user.setName(null));
            }

            @Test
            @DisplayName("Should throw exception when setting blank name")
            void shouldThrowExceptionForBlankName() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertThrows(IllegalArgumentException.class, () -> user.setName(""));
                assertThrows(IllegalArgumentException.class, () -> user.setName("   "));
            }

            @Test
            @DisplayName("Should preserve original name if setName fails validation")
            void shouldPreserveOriginalNameIfSetNameFails() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertThrows(IllegalArgumentException.class, () -> user.setName(""));
                assertEquals(TEST_NAME, user.getName());
            }
        }

        @Nested
        @DisplayName("setEmail() Tests")
        class SetEmailTests {

            @Test
            @DisplayName("Should set valid email successfully")
            void shouldSetValidEmailSuccessfully() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                user.setEmail(TEST_EMAIL_2);
                assertEquals(TEST_EMAIL_2, user.getEmail());
            }

            @Test
            @DisplayName("Should throw exception when setting null email")
            void shouldThrowExceptionForNullEmail() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertThrows(IllegalArgumentException.class, () -> user.setEmail(null));
            }

            @Test
            @DisplayName("Should throw exception when setting blank email")
            void shouldThrowExceptionForBlankEmail() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertThrows(IllegalArgumentException.class, () -> user.setEmail(""));
                assertThrows(IllegalArgumentException.class, () -> user.setEmail("   "));
            }

            @Test
            @DisplayName("Should preserve original email if setEmail fails validation")
            void shouldPreserveOriginalEmailIfSetEmailFails() {
                TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
                assertThrows(IllegalArgumentException.class, () -> user.setEmail(""));
                assertEquals(TEST_EMAIL, user.getEmail());
            }
        }
    }

    @Nested
    @DisplayName("Edge Cases and Integration Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle multiple users independently")
        void shouldHandleMultipleUsersIndependently() {
            TestUser user1 = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            TestUser user2 = new TestUser(VALID_DNI_2, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_NUMBER_2);

            assertEquals(VALID_DNI, user1.getId());
            assertEquals(VALID_DNI_2, user2.getId());
            assertEquals(TEST_NAME, user1.getName());
            assertEquals(TEST_NAME_2, user2.getName());

            user1.setName("Changed Name");
            assertEquals("Changed Name", user1.getName());
            assertEquals(TEST_NAME_2, user2.getName());
        }

        @Test
        @DisplayName("Should handle multiple setter calls sequentially")
        void shouldHandleMultipleSetterCallsSequentially() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            
            user.setName("New Name");
            assertEquals("New Name", user.getName());
            
            user.setEmail("new@email.com");
            assertEquals("new@email.com", user.getEmail());
            
            user.setId(VALID_DNI_2);
            assertEquals(VALID_DNI_2, user.getId());

            assertEquals("New Name", user.getName());
            assertEquals("new@email.com", user.getEmail());
        }

        @Test
        @DisplayName("Should implement Identificable interface")
        void shouldImplementIdentificableInterface() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            assertTrue(user instanceof classes.Identificable);
            assertEquals(VALID_DNI, user.getId());
        }

        @Test
        @DisplayName("Should maintain data consistency after multiple operations")
        void shouldMaintainDataConsistencyAfterMultipleOperations() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            
            // Perform multiple operations
            user.setName("Name1");
            user.setEmail("email1@test.com");
            user.setId(VALID_DNI_2);
            user.setName("Name2");
            user.setEmail("email2@test.com");

            // Verify final state
            assertEquals(VALID_DNI_2, user.getId());
            assertEquals("Name2", user.getName());
            assertEquals("email2@test.com", user.getEmail());
        }
    }

    @Nested
    @DisplayName("toString() Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return non-null string from toString()")
        void shouldReturnNonNullString() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            String result = user.toString();
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should include ID in toString() output")
        void shouldIncludeIdInToString() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            String result = user.toString();
            assertTrue(result.contains(VALID_DNI));
        }

        @Test
        @DisplayName("Should include name in toString() output")
        void shouldIncludeNameInToString() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            String result = user.toString();
            assertTrue(result.contains(TEST_NAME));
        }

        @Test
        @DisplayName("Should include lastName in toString() output")
        void shouldIncludeLastNameInToString() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            String result = user.toString();
            assertTrue(result.contains(TEST_LAST_NAME));
        }

        @Test
        @DisplayName("Should include email in toString() output")
        void shouldIncludeEmailInToString() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            String result = user.toString();
            assertTrue(result.contains(TEST_EMAIL));
        }

        @Test
        @DisplayName("Should include phoneNumber in toString() output")
        void shouldIncludePhoneNumberInToString() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            String result = user.toString();
            assertTrue(result.contains(TEST_PHONE_NUMBER));
        }

        @Test
        @DisplayName("Should use comma separator in toString()")
        void shouldUseCommaSeparator() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            String result = user.toString();
            String[] parts = result.split(",");
            assertEquals(5, parts.length);
        }

        @Test
        @DisplayName("Should format toString() in correct order")
        void shouldFormatInCorrectOrder() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            String result = user.toString();
            String[] parts = result.split(",");
            assertEquals(VALID_DNI, parts[0]);
            assertEquals(TEST_NAME, parts[1]);
            assertEquals(TEST_LAST_NAME, parts[2]);
            assertEquals(TEST_EMAIL, parts[3]);
            assertEquals(TEST_PHONE_NUMBER, parts[4]);
        }

        @Test
        @DisplayName("Should reflect updated values in toString()")
        void shouldReflectUpdatedValuesInToString() {
            TestUser user = new TestUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE_NUMBER);
            user.setName(TEST_NAME_2);
            user.setEmail(TEST_EMAIL_2);
            String result = user.toString();
            assertTrue(result.contains(TEST_NAME_2));
            assertTrue(result.contains(TEST_EMAIL_2));
            assertFalse(result.contains(TEST_NAME));
            assertFalse(result.contains(TEST_EMAIL));
        }
    }
}
