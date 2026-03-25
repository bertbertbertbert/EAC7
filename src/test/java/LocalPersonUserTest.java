import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.LocalPersonUser;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for LocalPersonUser class")
public class LocalPersonUserTest {

    // Test message constants
    private static final String CONSTRUCTOR_TESTS = "Constructor Tests";
    private static final String VALID_DNI_CREATION = "Should create a natural person user with valid DNI";
    private static final String INVALID_DNI_CHECK_LETTER = "Should throw IllegalArgumentException for invalid check letter";
    private static final String INVALID_DNI_TOO_SHORT = "Should throw exception for DNI that is too short";
    private static final String INVALID_DNI_TOO_LONG = "Should throw exception for DNI that is too long";
    
    private static final String VALIDATION_TESTS = "hasValidId() Tests";
    private static final String VALID_DNI_VALIDATION = "Should return true for valid DNI";
    private static final String INVALID_LETTER_VALIDATION = "Should return false for DNI with invalid check letter";
    
    private static final String GETTER_TESTS = "Getter Tests";
    private static final String GET_ID = "Should return the correct DNI";
    private static final String GET_LAST_NAME = "Should return the correct last name";
    private static final String GET_PHONE_NUMBER = "Should return the correct phone number";
    
    private static final String SETTER_TESTS = "Setter Tests";
    
    // Valid test DNI values (calculated using modulo 23)
    // 12345678 % 23 = 14 -> DNI_LETTERS.charAt(14) = 'Z'
    private static final String VALID_DNI = "12345678Z";
    // 87654321 % 23 = 10 -> DNI_LETTERS.charAt(10) = 'X'
    private static final String VALID_DNI_2 = "87654321X";
    // 11111111 % 23 = 18 -> DNI_LETTERS.charAt(18) = 'H'
    private static final String VALID_DNI_3 = "11111111H";
    
    // Invalid DNI values
    private static final String INVALID_CHECK_LETTER_DNI = "12345678A";  // Wrong check letter
    private static final String SHORT_DNI = "1234567";  // Only 7 digits
    private static final String LONG_DNI = "123456789Z";  // 9 digits + letter
    
    // Test data
    private static final String TEST_NAME = "John";
    private static final String TEST_LAST_NAME = "Doe";
    private static final String TEST_EMAIL = "john.doe@example.com";
    private static final String TEST_PHONE = "+34600123456";
    private static final String TEST_BIRTH_PLACE = "Madrid";
    private static final String TEST_PROVINCE = "Madrid";
    
    private static final String TEST_NAME_2 = "Jane";
    private static final String TEST_LAST_NAME_2 = "Smith";
    private static final String TEST_EMAIL_2 = "jane.smith@example.com";
    private static final String TEST_PHONE_2 = "+34600654321";

    @Nested
    @DisplayName(CONSTRUCTOR_TESTS)
    class ConstructorTests {

        @Test
        @DisplayName(VALID_DNI_CREATION)
        void shouldCreateLocalPersonUserWithValidDNI() {
            assertDoesNotThrow(() -> {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertNotNull(user);
                assertEquals(VALID_DNI, user.getId());
                assertEquals(TEST_NAME, user.getName());
                assertEquals(TEST_EMAIL, user.getEmail());
                assertEquals(TEST_LAST_NAME, user.getLastName());
                assertEquals(TEST_PHONE, user.getPhoneNumber());
            });
        }

        @Test
        @DisplayName("Should create user with another valid DNI")
        void shouldCreateLocalPersonUserWithAnotherValidDNI() {
            assertDoesNotThrow(() -> {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI_2, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertNotNull(user);
                assertEquals(VALID_DNI_2, user.getId());
            });
        }

        @Test
        @DisplayName(INVALID_DNI_CHECK_LETTER)
        void shouldThrowExceptionForInvalidCheckLetter() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(INVALID_CHECK_LETTER_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName(INVALID_DNI_TOO_SHORT)
        void shouldThrowExceptionForTooShortDNI() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(SHORT_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName(INVALID_DNI_TOO_LONG)
        void shouldThrowExceptionForTooLongDNI() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(LONG_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when DNI is null")
        void shouldThrowExceptionForNullId() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(null, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when DNI is blank")
        void shouldThrowExceptionForBlankId() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser("", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser("   ", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when name is null")
        void shouldThrowExceptionForNullName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, null, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when name is blank")
        void shouldThrowExceptionForBlankName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, "", TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, "   ", TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when email is null")
        void shouldThrowExceptionForNullEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, null, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when email is blank")
        void shouldThrowExceptionForBlankEmail() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, "", TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, "   ", TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when lastName is null")
        void shouldThrowExceptionForNullLastName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, null, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when lastName is blank")
        void shouldThrowExceptionForBlankLastName() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, "", TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, "   ", TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when phoneNumber is null")
        void shouldThrowExceptionForNullPhoneNumber() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, null, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }

        @Test
        @DisplayName("Should throw exception when phoneNumber is blank")
        void shouldThrowExceptionForBlankPhoneNumber() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, "", TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, "   ", TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }
    }

    @Nested
    @DisplayName(VALIDATION_TESTS)
    class ValidationTests {

        @Test
        @DisplayName(VALID_DNI_VALIDATION)
        void shouldValidateCorrectlyValidDNI() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertTrue(user.hasValidId());
        }

        @Test
        @DisplayName("Should validate multiple valid DNIs")
        void shouldValidateMultipleValidDNIs() {
            LocalPersonUser user1 = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            LocalPersonUser user2 = new LocalPersonUser(VALID_DNI_2, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_BIRTH_PLACE, TEST_PROVINCE);
            LocalPersonUser user3 = new LocalPersonUser(VALID_DNI_3, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            
            assertTrue(user1.hasValidId());
            assertTrue(user2.hasValidId());
            assertTrue(user3.hasValidId());
        }

        @Test
        @DisplayName(INVALID_LETTER_VALIDATION)
        void shouldRejectInvalidCheckLetter() {
            assertThrows(IllegalArgumentException.class, () -> {
                new LocalPersonUser(INVALID_CHECK_LETTER_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            });
        }
    }

    @Nested
    @DisplayName(GETTER_TESTS)
    class GetterTests {

        @Test
        @DisplayName(GET_ID)
        void shouldReturnCorrectDNI() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertEquals(VALID_DNI, user.getId());
        }

        @Test
        @DisplayName("Should normalize DNI to uppercase")
        void shouldNormalizeDNIToUppercase() {
            LocalPersonUser user = new LocalPersonUser("12345678z", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertEquals("12345678Z", user.getId());
        }

        @Test
        @DisplayName("Should trim whitespace from DNI")
        void shouldTrimWhitespaceFromDNI() {
            LocalPersonUser user = new LocalPersonUser("  12345678Z  ", TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertEquals("12345678Z", user.getId());
        }

        @Test
        @DisplayName("Should return correct name")
        void shouldReturnCorrectName() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertEquals(TEST_NAME, user.getName());
        }

        @Test
        @DisplayName("Should return correct email")
        void shouldReturnCorrectEmail() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertEquals(TEST_EMAIL, user.getEmail());
        }

        @Test
        @DisplayName(GET_LAST_NAME)
        void shouldReturnCorrectLastName() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertEquals(TEST_LAST_NAME, user.getLastName());
        }

        @Test
        @DisplayName(GET_PHONE_NUMBER)
        void shouldReturnCorrectPhoneNumber() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertEquals(TEST_PHONE, user.getPhoneNumber());
        }
    }

    @Nested
    @DisplayName(SETTER_TESTS)
    class SetterTests {

        @Nested
        @DisplayName("setLastName() Tests")
        class SetLastNameTests {

            @Test
            @DisplayName("Should set valid lastName successfully")
            void shouldSetValidLastNameSuccessfully() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                String newLastName = "Johnson";
                user.setLastName(newLastName);
                assertEquals(newLastName, user.getLastName());
            }

            @Test
            @DisplayName("Should throw exception when setting null lastName")
            void shouldThrowExceptionForNullLastName() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertThrows(IllegalArgumentException.class, () -> user.setLastName(null));
            }

            @Test
            @DisplayName("Should throw exception when setting blank lastName")
            void shouldThrowExceptionForBlankLastName() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertThrows(IllegalArgumentException.class, () -> user.setLastName(""));
                assertThrows(IllegalArgumentException.class, () -> user.setLastName("   "));
            }

            @Test
            @DisplayName("Should preserve original lastName if setLastName fails")
            void shouldPreserveOriginalLastNameIfSetLastNameFails() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertThrows(IllegalArgumentException.class, () -> user.setLastName(null));
                assertEquals(TEST_LAST_NAME, user.getLastName());
            }
        }

        @Nested
        @DisplayName("setPhoneNumber() Tests")
        class SetPhoneNumberTests {

            @Test
            @DisplayName("Should set valid phoneNumber successfully")
            void shouldSetValidPhoneNumberSuccessfully() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                String newPhone = "+34611222333";
                user.setPhoneNumber(newPhone);
                assertEquals(newPhone, user.getPhoneNumber());
            }

            @Test
            @DisplayName("Should throw exception when setting null phoneNumber")
            void shouldThrowExceptionForNullPhoneNumber() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(null));
            }

            @Test
            @DisplayName("Should throw exception when setting blank phoneNumber")
            void shouldThrowExceptionForBlankPhoneNumber() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(""));
                assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber("   "));
            }

            @Test
            @DisplayName("Should preserve original phoneNumber if setPhoneNumber fails")
            void shouldPreserveOriginalPhoneNumberIfSetPhoneNumberFails() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(null));
                assertEquals(TEST_PHONE, user.getPhoneNumber());
            }
        }

        @Nested
        @DisplayName("Inherited Setter Tests")
        class InheritedSetterTests {

            @Test
            @DisplayName("Should set valid name successfully")
            void shouldSetValidNameSuccessfully() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                String newName = "Michael";
                user.setName(newName);
                assertEquals(newName, user.getName());
            }

            @Test
            @DisplayName("Should set valid email successfully")
            void shouldSetValidEmailSuccessfully() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                String newEmail = "new@example.com";
                user.setEmail(newEmail);
                assertEquals(newEmail, user.getEmail());
            }

            @Test
            @DisplayName("Should set valid ID successfully")
            void shouldSetValidIdSuccessfully() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                user.setId(VALID_DNI_2);
                assertEquals(VALID_DNI_2, user.getId());
            }

            @Test
            @DisplayName("Should throw exception when setting invalid ID format")
            void shouldThrowExceptionForInvalidIdFormat() {
                LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
                assertThrows(IllegalArgumentException.class, () -> user.setId(INVALID_CHECK_LETTER_DNI));
            }
        }
    }

    @Nested
    @DisplayName("Edge Cases and Integration Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle multiple instances independently")
        void shouldHandleMultipleInstancesIndependently() {
            LocalPersonUser user1 = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            LocalPersonUser user2 = new LocalPersonUser(VALID_DNI_2, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_BIRTH_PLACE, TEST_PROVINCE);

            assertEquals(VALID_DNI, user1.getId());
            assertEquals(VALID_DNI_2, user2.getId());
            assertNotEquals(user1.getId(), user2.getId());
        }

        @Test
        @DisplayName("Should calculate check letter correctly based on modulo 23")
        void shouldCalculateCheckLetterCorrectly() {
            // For 12345678, 12345678 % 23 = 14, so check letter should be DNI_LETTERS.charAt(14) = 'Z'
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertTrue(user.hasValidId());
        }

        @Test
        @DisplayName("Should handle multiple setter calls sequentially")
        void shouldHandleMultipleSetterCallsSequentially() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            
            user.setLastName("NewLastName");
            assertEquals("NewLastName", user.getLastName());
            
            user.setPhoneNumber("+34999888777");
            assertEquals("+34999888777", user.getPhoneNumber());
            
            user.setName("NewName");
            assertEquals("NewName", user.getName());
            
            user.setEmail("newemail@test.com");
            assertEquals("newemail@test.com", user.getEmail());

            assertEquals("NewLastName", user.getLastName());
            assertEquals("+34999888777", user.getPhoneNumber());
        }

        @Test
        @DisplayName("Should maintain data consistency after multiple operations")
        void shouldMaintainDataConsistencyAfterMultipleOperations() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            
            user.setName("Name1");
            user.setEmail("email1@test.com");
            user.setLastName("LastName1");
            user.setPhoneNumber("+34111111111");
            user.setId(VALID_DNI_2);
            user.setName("Name2");
            user.setPhoneNumber("+34222222222");

            assertEquals(VALID_DNI_2, user.getId());
            assertEquals("Name2", user.getName());
            assertEquals("email1@test.com", user.getEmail());
            assertEquals("LastName1", user.getLastName());
            assertEquals("+34222222222", user.getPhoneNumber());
        }

        @Test
        @DisplayName("Should implement Identificable interface")
        void shouldImplementIdentificableInterface() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            assertTrue(user instanceof classes.Identificable);
            assertEquals(VALID_DNI, user.getId());
        }
    }

    @Nested
    @DisplayName("toString() Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return non-null string from toString()")
        void shouldReturnNonNullString() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            String result = user.toString();
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should include all parent User fields in toString()")
        void shouldIncludeParentUserFields() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            String result = user.toString();
            assertTrue(result.contains(VALID_DNI));
            assertTrue(result.contains(TEST_NAME));
            assertTrue(result.contains(TEST_EMAIL));
            assertTrue(result.contains(TEST_LAST_NAME));
            assertTrue(result.contains(TEST_PHONE));
        }

        @Test
        @DisplayName("Should include birthPlace in toString()")
        void shouldIncludeBirthPlaceInToString() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            String result = user.toString();
            assertTrue(result.contains(TEST_BIRTH_PLACE));
        }

        @Test
        @DisplayName("Should include province in toString()")
        void shouldIncludeProvinceInToString() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            String result = user.toString();
            assertTrue(result.contains(TEST_PROVINCE));
        }

        @Test
        @DisplayName("Should use comma separator in toString()")
        void shouldUseCommaSeparator() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            String result = user.toString();
            String[] parts = result.split(",");
            assertEquals(7, parts.length);
        }

        @Test
        @DisplayName("Should format toString() with correct field order")
        void shouldFormatInCorrectOrder() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            String result = user.toString();
            String[] parts = result.split(",");
            assertEquals(VALID_DNI, parts[0]);
            assertEquals(TEST_NAME, parts[1]);
            assertEquals(TEST_LAST_NAME, parts[2]);
            assertEquals(TEST_EMAIL, parts[3]);
            assertEquals(TEST_PHONE, parts[4]);
            assertEquals(TEST_BIRTH_PLACE, parts[5]);
            assertEquals(TEST_PROVINCE, parts[6]);
        }

        @Test
        @DisplayName("Should reflect updated birthPlace in toString()")
        void shouldReflectUpdatedBirthPlace() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, "Seville", "Andalusia");
            String initialResult = user.toString();
            assertTrue(initialResult.contains("Seville"));
            user.setBirthPlace("Barcelona");
            String result = user.toString();
            assertTrue(result.contains("Barcelona"));
            assertFalse(result.contains("Seville"));
        }

        @Test
        @DisplayName("Should reflect updated province in toString()")
        void shouldReflectUpdatedProvince() {
            LocalPersonUser user = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, "Valencia", "Valencian Community");
            String initialResult = user.toString();
            assertTrue(initialResult.contains("Valencian Community"));
            user.setProvince("Cataluña");
            String result = user.toString();
            assertTrue(result.contains("Cataluña"));
            assertFalse(result.contains("Valencian Community"));
        }

        @Test
        @DisplayName("Should work with different DNI values")
        void shouldWorkWithDifferentDNIValues() {
            LocalPersonUser user1 = new LocalPersonUser(VALID_DNI, TEST_NAME, TEST_EMAIL, TEST_LAST_NAME, TEST_PHONE, TEST_BIRTH_PLACE, TEST_PROVINCE);
            LocalPersonUser user2 = new LocalPersonUser(VALID_DNI_2, TEST_NAME_2, TEST_EMAIL_2, TEST_LAST_NAME_2, TEST_PHONE_2, TEST_BIRTH_PLACE, TEST_PROVINCE);
            
            String result1 = user1.toString();
            String result2 = user2.toString();
            
            assertTrue(result1.contains(VALID_DNI));
            assertTrue(result2.contains(VALID_DNI_2));
            assertNotEquals(result1, result2);
        }
    }
}
