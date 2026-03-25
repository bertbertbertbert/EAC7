import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.ForeignPersonUser;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for ForeignPersonUser class")
public class ForeignPersonUserTest {

    // Test message constants
    private static final String CONSTRUCTOR_TESTS = "Constructor Tests";
    private static final String VALID_NIE_X_PREFIX = "Should create a foreign person user with valid NIE starting with X";
    private static final String VALID_NIE_Y_PREFIX = "Should create a foreign person user with valid NIE starting with Y";
    private static final String VALID_NIE_Z_PREFIX = "Should create a foreign person user with valid NIE starting with Z";
    private static final String INVALID_NIE_PREFIX = "Should throw IllegalArgumentException for invalid NIE prefix";
    private static final String INVALID_NIE_CHECK_LETTER = "Should throw IllegalArgumentException for invalid check letter";
    private static final String INVALID_NIE_TOO_SHORT = "Should throw exception for NIE that is too short";
    private static final String INVALID_NIE_TOO_LONG = "Should throw exception for NIE that is too long";
    
    private static final String VALIDATION_TESTS = "hasValidId() Tests";
    private static final String VALID_NIE_X_VALIDATION = "Should return true for valid NIE with X prefix";
    private static final String VALID_NIE_Y_VALIDATION = "Should return true for valid NIE with Y prefix";
    private static final String VALID_NIE_Z_VALIDATION = "Should return true for valid NIE with Z prefix";
    
    private static final String GETTER_TESTS = "Getter Tests";
    private static final String GET_ID = "Should return the correct NIE";
    
    // Valid test NIE values (calculated using modulo 23)
    // X prefix: 0 + 1234567 = 01234567, 01234567 % 23 = 7 -> DNI_LETTERS.charAt(7) = 'L'
    private static final String VALID_NIE_X = "X1234567L";
    // Y prefix: 1 + 1234567 = 11234567, 11234567 % 23 = 10 -> DNI_LETTERS.charAt(10) = 'X'
    private static final String VALID_NIE_Y = "Y1234567X";
    // Z prefix: 2 + 1234567 = 21234567, 21234567 % 23 = 1 -> DNI_LETTERS.charAt(1) = 'R'
    private static final String VALID_NIE_Z = "Z1234567R";
    
    // Additional valid NIE values
    // X prefix: 0 + 9999999 = 09999999, 09999999 % 23 = 13 -> DNI_LETTERS.charAt(13) = 'J'
    private static final String VALID_NIE_X_2 = "X9999999J";
    // Y prefix: 1 + 8888888 = 18888888, 18888888 % 23 = 0 -> DNI_LETTERS.charAt(0) = 'T'
    private static final String VALID_NIE_Y_2 = "Y8888888T";
    // Z prefix: 2 + 7777777 = 27777777, 27777777 % 23 = 10 -> DNI_LETTERS.charAt(10) = 'X'
    private static final String VALID_NIE_Z_2 = "Z7777777X";
    
    // Invalid NIE values
    private static final String INVALID_PREFIX_NIE = "W12345670";  // Invalid prefix
    private static final String INVALID_CHECK_LETTER_NIE = "X1234567A";  // Wrong check letter
    private static final String SHORT_NIE = "X123456";  // Only 8 characters total
    private static final String LONG_NIE = "X123456789";  // 10 characters
    
    // Test data
    private static final String TEST_NAME = "Juan Carlos";
    private static final String TEST_EMAIL = "juan.carlos@example.com";
    private static final String TEST_LAST_NAME = "Garcia Lopez";
    private static final String TEST_PHONE = "555-123-4567";
    private static final String TEST_NATIONALITY = "Mexico";
    
    private static final String TEST_NAME_2 = "Maria Isabel";
    private static final String TEST_EMAIL_2 = "maria.isabel@example.com";
    private static final String TEST_LAST_NAME_2 = "Martinez Rodriguez";
    private static final String TEST_PHONE_2 = "555-987-6543";

    @Nested
    @DisplayName(CONSTRUCTOR_TESTS)
    class ConstructorTests {

        @Test
        @DisplayName(VALID_NIE_X_PREFIX)
        void shouldCreateForeignPersonUserWithValidNIEX() {
            assertDoesNotThrow(() -> {
                ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
                assertNotNull(user);
                assertEquals(VALID_NIE_X, user.getId());
            });
        }

        @Test
        @DisplayName(VALID_NIE_Y_PREFIX)
        void shouldCreateForeignPersonUserWithValidNIEY() {
            assertDoesNotThrow(() -> {
                ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_Y, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_NATIONALITY);
                assertNotNull(user);
                assertEquals(VALID_NIE_Y, user.getId());
            });
        }

        @Test
        @DisplayName(VALID_NIE_Z_PREFIX)
        void shouldCreateForeignPersonUserWithValidNIEZ() {
            assertDoesNotThrow(() -> {
                ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_Z, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
                assertNotNull(user);
                assertEquals(VALID_NIE_Z, user.getId());
            });
        }

        @Test
        @DisplayName("Should create user with another valid NIE X")
        void shouldCreateForeignPersonUserWithAnotherValidNIEX() {
            assertDoesNotThrow(() -> {
                ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X_2, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_NATIONALITY);
                assertNotNull(user);
                assertEquals(VALID_NIE_X_2, user.getId());
            });
        }

        @Test
        @DisplayName("Should create user with another valid NIE Y")
        void shouldCreateForeignPersonUserWithAnotherValidNIEY() {
            assertDoesNotThrow(() -> {
                ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_Y_2, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
                assertNotNull(user);
                assertEquals(VALID_NIE_Y_2, user.getId());
            });
        }

        @Test
        @DisplayName("Should create user with another valid NIE Z")
        void shouldCreateForeignPersonUserWithAnotherValidNIEZ() {
            assertDoesNotThrow(() -> {
                ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_Z_2, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_NATIONALITY);
                assertNotNull(user);
                assertEquals(VALID_NIE_Z_2, user.getId());
            });
        }

        @Test
        @DisplayName(INVALID_NIE_PREFIX)
        void shouldThrowExceptionForInvalidNIEPrefix() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(INVALID_PREFIX_NIE, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName(INVALID_NIE_CHECK_LETTER)
        void shouldThrowExceptionForInvalidCheckLetter() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(INVALID_CHECK_LETTER_NIE, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName(INVALID_NIE_TOO_SHORT)
        void shouldThrowExceptionForTooShortNIE() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(SHORT_NIE, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName(INVALID_NIE_TOO_LONG)
        void shouldThrowExceptionForTooLongNIE() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(LONG_NIE, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when NIE is null")
        void shouldThrowExceptionForNullId() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(null, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when NIE is blank")
        void shouldThrowExceptionForBlankId() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser("", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser("   ", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when name is null")
        void shouldThrowExceptionForNullName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, null, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when name is blank")
        void shouldThrowExceptionForBlankName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, "", TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, "   ", TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when email is null")
        void shouldThrowExceptionForNullEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, null, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when email is blank")
        void shouldThrowExceptionForBlankEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, "", TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, "   ", TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when lastName is null")
        void shouldThrowExceptionForNullLastName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, null, TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when lastName is blank")
        void shouldThrowExceptionForBlankLastName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, "", TEST_PHONE, TEST_NATIONALITY);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, "   ", TEST_PHONE, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when phoneNumber is null")
        void shouldThrowExceptionForNullPhoneNumber() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, null, TEST_NATIONALITY);
            });
        }

        @Test
        @DisplayName("Should throw exception when phoneNumber is blank")
        void shouldThrowExceptionForBlankPhoneNumber() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, "", TEST_NATIONALITY);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, "   ", TEST_NATIONALITY);
            });
        }
    }

    @Nested
    @DisplayName(VALIDATION_TESTS)
    class ValidationTests {

        @Test
        @DisplayName(VALID_NIE_X_VALIDATION)
        void shouldValidateCorrectlyNIEWithXPrefix() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertTrue(user.hasValidId());
        }

        @Test
        @DisplayName(VALID_NIE_Y_VALIDATION)
        void shouldValidateCorrectlyNIEWithYPrefix() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_Y, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertTrue(user.hasValidId());
        }

        @Test
        @DisplayName(VALID_NIE_Z_VALIDATION)
        void shouldValidateCorrectlyNIEWithZPrefix() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_Z, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertTrue(user.hasValidId());
        }
    }

    @Nested
    @DisplayName(GETTER_TESTS)
    class GetterTests {

        @Test
        @DisplayName(GET_ID)
        void shouldReturnCorrectNIE() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertEquals(VALID_NIE_X, user.getId());
        }

        @Test
        @DisplayName("Should normalize NIE to uppercase")
        void shouldNormalizeNIEToUppercase() {
            ForeignPersonUser user = new ForeignPersonUser("x1234567l", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertEquals("X1234567L", user.getId());
        }

        @Test
        @DisplayName("Should trim whitespace from NIE")
        void shouldTrimWhitespaceFromNIE() {
            ForeignPersonUser user = new ForeignPersonUser("  X1234567L  ", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertEquals("X1234567L", user.getId());
        }

        @Test
        @DisplayName("Should return correct name")
        void shouldReturnCorrectName() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertEquals(TEST_NAME, user.getName());
        }

        @Test
        @DisplayName("Should return correct email")
        void shouldReturnCorrectEmail() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertEquals(TEST_EMAIL, user.getEmail());
        }

        @Test
        @DisplayName("Should return correct lastName")
        void shouldReturnCorrectLastName() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertEquals(TEST_LAST_NAME, user.getLastName());
        }

        @Test
        @DisplayName("Should return correct phoneNumber")
        void shouldReturnCorrectPhoneNumber() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertEquals(TEST_PHONE, user.getPhoneNumber());
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {

        @Test
        @DisplayName("Should set valid name successfully")
        void shouldSetValidNameSuccessfully() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String newName = "Carlos Antonio";
            user.setName(newName);
            assertEquals(newName, user.getName());
        }

        @Test
        @DisplayName("Should throw exception when setting null name")
        void shouldThrowExceptionForNullName() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertThrows(IllegalArgumentException.class, () -> user.setName(null));
        }

        @Test
        @DisplayName("Should throw exception when setting blank name")
        void shouldThrowExceptionForBlankName() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertThrows(IllegalArgumentException.class, () -> user.setName(""));
            assertThrows(IllegalArgumentException.class, () -> user.setName("   "));
        }

        @Test
        @DisplayName("Should set valid email successfully")
        void shouldSetValidEmailSuccessfully() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String newEmail = "newemail@example.com";
            user.setEmail(newEmail);
            assertEquals(newEmail, user.getEmail());
        }

        @Test
        @DisplayName("Should throw exception when setting null email")
        void shouldThrowExceptionForNullEmail() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertThrows(IllegalArgumentException.class, () -> user.setEmail(null));
        }

        @Test
        @DisplayName("Should throw exception when setting blank email")
        void shouldThrowExceptionForBlankEmail() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertThrows(IllegalArgumentException.class, () -> user.setEmail(""));
            assertThrows(IllegalArgumentException.class, () -> user.setEmail("   "));
        }

        @Test
        @DisplayName("Should set valid lastName successfully")
        void shouldSetValidLastNameSuccessfully() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String newLastName = "Sanchez Garcia";
            user.setLastName(newLastName);
            assertEquals(newLastName, user.getLastName());
        }

        @Test
        @DisplayName("Should throw exception when setting null lastName")
        void shouldThrowExceptionForNullLastName() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertThrows(IllegalArgumentException.class, () -> user.setLastName(null));
        }

        @Test
        @DisplayName("Should throw exception when setting blank lastName")
        void shouldThrowExceptionForBlankLastName() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertThrows(IllegalArgumentException.class, () -> user.setLastName(""));
            assertThrows(IllegalArgumentException.class, () -> user.setLastName("   "));
        }

        @Test
        @DisplayName("Should set valid phoneNumber successfully")
        void shouldSetValidPhoneNumberSuccessfully() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String newPhone = "555-555-5555";
            user.setPhoneNumber(newPhone);
            assertEquals(newPhone, user.getPhoneNumber());
        }

        @Test
        @DisplayName("Should throw exception when setting null phoneNumber")
        void shouldThrowExceptionForNullPhoneNumber() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(null));
        }

        @Test
        @DisplayName("Should throw exception when setting blank phoneNumber")
        void shouldThrowExceptionForBlankPhoneNumber() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(""));
            assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber("   "));
        }

        @Test
        @DisplayName("Should set valid ID successfully")
        void shouldSetValidIdSuccessfully() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            user.setId(VALID_NIE_Y);
            assertEquals(VALID_NIE_Y, user.getId());
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle multiple instances independently")
        void shouldHandleMultipleInstancesIndependently() {
            ForeignPersonUser user1 = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            ForeignPersonUser user2 = new ForeignPersonUser(VALID_NIE_Y, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_NATIONALITY);

            assertEquals(VALID_NIE_X, user1.getId());
            assertEquals(VALID_NIE_Y, user2.getId());
            assertNotEquals(user1.getId(), user2.getId());
        }

        @Test
        @DisplayName("Should calculate check letter correctly based on modulo 23")
        void shouldCalculateCheckLetterCorrectly() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertTrue(user.hasValidId());
        }

        @Test
        @DisplayName("Should preserve all fields after creation")
        void shouldPreserveAllFieldsAfterCreation() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            assertEquals(VALID_NIE_X, user.getId());
            assertEquals(TEST_NAME, user.getName());
            assertEquals(TEST_EMAIL, user.getEmail());
            assertEquals(TEST_LAST_NAME, user.getLastName());
            assertEquals(TEST_PHONE, user.getPhoneNumber());
        }

        @Test
        @DisplayName("Should allow independent modification of all fields")
        void shouldAllowIndependentModificationOfAllFields() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            
            user.setId(VALID_NIE_Y_2);
            assertEquals(VALID_NIE_Y_2, user.getId());
            assertEquals(TEST_NAME, user.getName()); // Verify other fields unchanged
            
            user.setName("New Name");
            assertEquals("New Name", user.getName());
            assertEquals(VALID_NIE_Y_2, user.getId()); // Verify ID still correct
            
            user.setLastName("New LastName");
            assertEquals("New LastName", user.getLastName());
            assertEquals("New Name", user.getName()); // Verify name still correct
        }

        @Test
        @DisplayName("Should handle all three NIE prefixes for foreign persons")
        void shouldHandleAllThreeNIEPrefixes() {
            ForeignPersonUser userX = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            ForeignPersonUser userY = new ForeignPersonUser(VALID_NIE_Y, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            ForeignPersonUser userZ = new ForeignPersonUser(VALID_NIE_Z, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);

            assertTrue(userX.hasValidId());
            assertTrue(userY.hasValidId());
            assertTrue(userZ.hasValidId());
        }
    }

    @Nested
    @DisplayName("toString() Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return non-null string from toString()")
        void shouldReturnNonNullString() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String result = user.toString();
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should include all parent User fields in toString()")
        void shouldIncludeParentUserFields() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String result = user.toString();
            assertTrue(result.contains(VALID_NIE_X));
            assertTrue(result.contains(TEST_NAME));
            assertTrue(result.contains(TEST_EMAIL));
            assertTrue(result.contains(TEST_LAST_NAME));
            assertTrue(result.contains(TEST_PHONE));
        }

        @Test
        @DisplayName("Should include nationality in toString()")
        void shouldIncludeNationalityInToString() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String result = user.toString();
            assertTrue(result.contains(TEST_NATIONALITY));
        }

        @Test
        @DisplayName("Should use comma separator in toString()")
        void shouldUseCommaSeparator() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String result = user.toString();
            String[] parts = result.split(",");
            assertEquals(6, parts.length);
        }

        @Test
        @DisplayName("Should format toString() with correct field order")
        void shouldFormatInCorrectOrder() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String result = user.toString();
            String[] parts = result.split(",");
            assertEquals(VALID_NIE_X, parts[0]);
            assertEquals(TEST_NAME, parts[1]);
            assertEquals(TEST_LAST_NAME, parts[2]);
            assertEquals(TEST_EMAIL, parts[3]);
            assertEquals(TEST_PHONE, parts[4]);
            assertEquals(TEST_NATIONALITY, parts[5]);
        }

        @Test
        @DisplayName("Should reflect updated nationality in toString()")
        void shouldReflectUpdatedNationality() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            user.setNationality("France");
            String result = user.toString();
            assertTrue(result.contains("France"));
            assertFalse(result.contains(TEST_NATIONALITY));
        }

        @Test
        @DisplayName("Should work with NIE X prefix")
        void shouldWorkWithNIEXPrefix() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String result = user.toString();
            assertTrue(result.contains(VALID_NIE_X));
        }

        @Test
        @DisplayName("Should work with NIE Y prefix")
        void shouldWorkWithNIEYPrefix() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_Y, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_NATIONALITY);
            String result = user.toString();
            assertTrue(result.contains(VALID_NIE_Y));
        }

        @Test
        @DisplayName("Should work with NIE Z prefix")
        void shouldWorkWithNIEZPrefix() {
            ForeignPersonUser user = new ForeignPersonUser(VALID_NIE_Z, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            String result = user.toString();
            assertTrue(result.contains(VALID_NIE_Z));
        }

        @Test
        @DisplayName("Should differentiate between different users in toString()")
        void shouldDifferentiateBetweenUsers() {
            ForeignPersonUser user1 = new ForeignPersonUser(VALID_NIE_X, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_NATIONALITY);
            ForeignPersonUser user2 = new ForeignPersonUser(VALID_NIE_Y, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, "Brazil");
            
            String result1 = user1.toString();
            String result2 = user2.toString();
            
            assertNotEquals(result1, result2);
        }
    }
}
