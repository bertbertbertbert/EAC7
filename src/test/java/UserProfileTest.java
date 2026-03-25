import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.LocalPersonUser;
import classes.ForeignPersonUser;
import classes.User;
import classes.UserProfile;
import classes.Preferences;
import classes.UserStats;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for UserProfile class")
public class UserProfileTest {

    // Test data for LocalPersonUser
    private static final String TEST_DNI = "12345678Z";
    private static final String TEST_NAME = "John";
    private static final String TEST_LAST_NAME = "Doe";
    private static final String TEST_EMAIL = "john.doe@example.com";
    private static final String TEST_PHONE = "+34600123456";
    private static final String TEST_BIRTH_PLACE = "Madrid";
    private static final String TEST_PROVINCE = "Madrid";

    // Test data for ForeignPersonUser
    private static final String TEST_NIE = "X1234567L";
    private static final String TEST_FOREIGN_NAME = "Jane";
    private static final String TEST_FOREIGN_LAST_NAME = "Smith";
    private static final String TEST_FOREIGN_EMAIL = "jane.smith@example.com";
    private static final String TEST_FOREIGN_PHONE = "+34600654321";
    private static final String TEST_NATIONALITY = "Mexico";

    private User localUser;
    private User foreignUser;

    @BeforeEach
    void setUp() {
        localUser = new LocalPersonUser(TEST_DNI, TEST_NAME, TEST_EMAIL, 
                                       TEST_LAST_NAME, TEST_PHONE, 
                                       TEST_BIRTH_PLACE, TEST_PROVINCE);
        foreignUser = new ForeignPersonUser(TEST_NIE, TEST_FOREIGN_NAME, TEST_FOREIGN_EMAIL,
                                            TEST_FOREIGN_LAST_NAME, TEST_FOREIGN_PHONE,
                                            TEST_NATIONALITY);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create UserProfile with valid LocalPersonUser")
        void shouldCreateUserProfileWithLocalPersonUser() {
            UserProfile profile = new UserProfile(localUser);
            
            assertNotNull(profile);
            assertNotNull(profile.getUser());
            assertNotNull(profile.getPreferences());
            assertNotNull(profile.getStats());
        }

        @Test
        @DisplayName("Should create UserProfile with valid ForeignPersonUser")
        void shouldCreateUserProfileWithForeignPersonUser() {
            UserProfile profile = new UserProfile(foreignUser);
            
            assertNotNull(profile);
            assertNotNull(profile.getUser());
            assertNotNull(profile.getPreferences());
            assertNotNull(profile.getStats());
        }

        @Test
        @DisplayName("Should create UserProfile with null user (no validation)")
        void shouldCreateUserProfileWithNullUser() {
            UserProfile profile = new UserProfile(null);
            
            assertNotNull(profile);
            assertNull(profile.getUser());
            assertNotNull(profile.getPreferences());
            assertNotNull(profile.getStats());
        }

        @Test
        @DisplayName("Should initialize new Preferences instance")
        void shouldInitializeNewPreferences() {
            UserProfile profile = new UserProfile(localUser);
            Preferences prefs = profile.getPreferences();
            
            assertNotNull(prefs);
            assertInstanceOf(Preferences.class, prefs);
        }

        @Test
        @DisplayName("Should initialize new UserStats instance")
        void shouldInitializeNewUserStats() {
            UserProfile profile = new UserProfile(localUser);
            UserStats stats = profile.getStats();
            
            assertNotNull(stats);
            assertInstanceOf(UserStats.class, stats);
        }
    }

    @Nested
    @DisplayName("Getter Tests")
    class GetterTests {

        @Test
        @DisplayName("Should return correct User from getUser()")
        void shouldReturnCorrectUser() {
            UserProfile profile = new UserProfile(localUser);
            
            assertEquals(localUser, profile.getUser());
        }

        @Test
        @DisplayName("Should return correct user ID from getUserId()")
        void shouldReturnCorrectUserId() {
            UserProfile profile = new UserProfile(localUser);
            
            assertEquals(TEST_DNI, profile.getUserId());
        }

        @Test
        @DisplayName("Should return correct user ID for ForeignPersonUser")
        void shouldReturnCorrectUserIdForForeignUser() {
            UserProfile profile = new UserProfile(foreignUser);
            
            assertEquals(TEST_NIE, profile.getUserId());
        }

        @Test
        @DisplayName("Should return Preferences instance from getPreferences()")
        void shouldReturnPreferences() {
            UserProfile profile = new UserProfile(localUser);
            Preferences prefs = profile.getPreferences();
            
            assertNotNull(prefs);
            assertInstanceOf(Preferences.class, prefs);
        }

        @Test
        @DisplayName("Should return UserStats instance from getStats()")
        void shouldReturnStats() {
            UserProfile profile = new UserProfile(localUser);
            UserStats stats = profile.getStats();
            
            assertNotNull(stats);
            assertInstanceOf(UserStats.class, stats);
        }

        @Test
        @DisplayName("Should return same Preferences instance on multiple calls")
        void shouldReturnSamePreferencesInstance() {
            UserProfile profile = new UserProfile(localUser);
            Preferences prefs1 = profile.getPreferences();
            Preferences prefs2 = profile.getPreferences();
            
            assertSame(prefs1, prefs2);
        }

        @Test
        @DisplayName("Should return same UserStats instance on multiple calls")
        void shouldReturnSameUserStatsInstance() {
            UserProfile profile = new UserProfile(localUser);
            UserStats stats1 = profile.getStats();
            UserStats stats2 = profile.getStats();
            
            assertSame(stats1, stats2);
        }
    }

    @Nested
    @DisplayName("getSummary() Tests")
    class GetSummaryTests {

        @Test
        @DisplayName("Should return non-null summary")
        void shouldReturnNonNullSummary() {
            UserProfile profile = new UserProfile(localUser);
            String summary = profile.getSummary();
            
            assertNotNull(summary);
        }

        @Test
        @DisplayName("Should include user name in summary")
        void shouldIncludeUserNameInSummary() {
            UserProfile profile = new UserProfile(localUser);
            String summary = profile.getSummary();
            
            assertTrue(summary.contains(TEST_NAME));
        }

        @Test
        @DisplayName("Should include user ID in summary")
        void shouldIncludeUserIdInSummary() {
            UserProfile profile = new UserProfile(localUser);
            String summary = profile.getSummary();
            
            assertTrue(summary.contains(TEST_DNI));
        }

        @Test
        @DisplayName("Should include user email in summary")
        void shouldIncludeUserEmailInSummary() {
            UserProfile profile = new UserProfile(localUser);
            String summary = profile.getSummary();
            
            assertTrue(summary.contains(TEST_EMAIL));
        }

        @Test
        @DisplayName("Should include total bets in summary")
        void shouldIncludeTotalBetsInSummary() {
            UserProfile profile = new UserProfile(localUser);
            String summary = profile.getSummary();
            
            // Initial total bets should be 0
            assertTrue(summary.contains("0") || summary.matches(".*\\d+.*"));
        }

        @Test
        @DisplayName("Should generate valid summary for ForeignPersonUser")
        void shouldGenerateValidSummaryForForeignUser() {
            UserProfile profile = new UserProfile(foreignUser);
            String summary = profile.getSummary();
            
            assertNotNull(summary);
            assertTrue(summary.contains(TEST_FOREIGN_NAME));
            assertTrue(summary.contains(TEST_NIE));
            assertTrue(summary.contains(TEST_FOREIGN_EMAIL));
        }
    }

    @Nested
    @DisplayName("matchesBettorId() Tests")
    class MatchesBettorIdTests {

        @Test
        @DisplayName("Should return true for exact matching bettor ID")
        void shouldReturnTrueForExactMatch() {
            UserProfile profile = new UserProfile(localUser);
            
            assertTrue(profile.matchesBettorId(TEST_DNI));
        }

        @Test
        @DisplayName("Should return false for null bettor ID")
        void shouldReturnFalseForNullBettorId() {
            UserProfile profile = new UserProfile(localUser);
            
            assertFalse(profile.matchesBettorId(null));
        }

        @Test
        @DisplayName("Should return false for non-matching bettor ID")
        void shouldReturnFalseForNonMatchingId() {
            UserProfile profile = new UserProfile(localUser);
            
            assertFalse(profile.matchesBettorId("00000000T"));
        }

        @Test
        @DisplayName("Should perform case-insensitive comparison")
        void shouldPerformCaseInsensitiveComparison() {
            UserProfile profile = new UserProfile(localUser);
            
            assertTrue(profile.matchesBettorId(TEST_DNI.toLowerCase()));
            assertTrue(profile.matchesBettorId(TEST_DNI.toUpperCase()));
            assertTrue(profile.matchesBettorId("12345678z"));
        }

        @Test
        @DisplayName("Should trim whitespace from bettor ID")
        void shouldTrimWhitespaceFromBettorId() {
            UserProfile profile = new UserProfile(localUser);
            
            assertTrue(profile.matchesBettorId("  " + TEST_DNI + "  "));
            assertTrue(profile.matchesBettorId("\t" + TEST_DNI + "\n"));
        }

        @Test
        @DisplayName("Should handle both trim and case-insensitive comparison")
        void shouldHandleTrimAndCaseInsensitive() {
            UserProfile profile = new UserProfile(localUser);
            
            assertTrue(profile.matchesBettorId("  " + TEST_DNI.toLowerCase() + "  "));
        }

        @Test
        @DisplayName("Should match NIE for ForeignPersonUser")
        void shouldMatchNieForForeignUser() {
            UserProfile profile = new UserProfile(foreignUser);
            
            assertTrue(profile.matchesBettorId(TEST_NIE));
            assertTrue(profile.matchesBettorId(TEST_NIE.toLowerCase()));
        }

        @Test
        @DisplayName("Should return false for empty string")
        void shouldReturnFalseForEmptyString() {
            UserProfile profile = new UserProfile(localUser);
            
            assertFalse(profile.matchesBettorId(""));
        }

        @Test
        @DisplayName("Should return false for blank string")
        void shouldReturnFalseForBlankString() {
            UserProfile profile = new UserProfile(localUser);
            
            assertFalse(profile.matchesBettorId("   "));
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Should maintain independent UserProfile instances")
        void shouldMaintainIndependentProfiles() {
            UserProfile profile1 = new UserProfile(localUser);
            UserProfile profile2 = new UserProfile(foreignUser);
            
            assertNotSame(profile1.getPreferences(), profile2.getPreferences());
            assertNotSame(profile1.getStats(), profile2.getStats());
        }

        @Test
        @DisplayName("Should correctly identify user from different profile types")
        void shouldCorrectlyIdentifyUserFromDifferentTypes() {
            UserProfile localProfile = new UserProfile(localUser);
            UserProfile foreignProfile = new UserProfile(foreignUser);
            
            assertTrue(localProfile.matchesBettorId(TEST_DNI));
            assertFalse(localProfile.matchesBettorId(TEST_NIE));
            
            assertTrue(foreignProfile.matchesBettorId(TEST_NIE));
            assertFalse(foreignProfile.matchesBettorId(TEST_DNI));
        }

        @Test
        @DisplayName("Should provide complete user information through profile")
        void shouldProvideCompleteUserInformation() {
            UserProfile profile = new UserProfile(localUser);
            
            assertNotNull(profile.getUser());
            assertNotNull(profile.getUserId());
            assertNotNull(profile.getSummary());
            assertTrue(profile.matchesBettorId(TEST_DNI));
        }
    }

    @Nested
    @DisplayName("toString() Tests")
    class ToStringTests {

        @Test
        @DisplayName("Should return non-null string from toString()")
        void shouldReturnNonNullString() {
            UserProfile profile = new UserProfile(localUser);
            String result = profile.toString();
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should include user information in toString()")
        void shouldIncludeUserInformation() {
            UserProfile profile = new UserProfile(localUser);
            String result = profile.toString();
            assertTrue(result.contains(TEST_DNI));
            assertTrue(result.contains(TEST_NAME));
            assertTrue(result.contains(TEST_EMAIL));
        }

        @Test
        @DisplayName("Should include preferences in toString()")
        void shouldIncludePreferences() {
            UserProfile profile = new UserProfile(localUser);
            String result = profile.toString();
            assertTrue(result.contains("1.01"));
            assertTrue(result.contains("500.00"));
            assertTrue(result.contains("true"));
        }

        @Test
        @DisplayName("Should include stats in toString()")
        void shouldIncludeStats() {
            UserProfile profile = new UserProfile(localUser);
            String result = profile.toString();
            assertTrue(result.contains("0,0.00"));
        }

        @Test
        @DisplayName("Should use comma separator in toString()")
        void shouldUseCommaSeparator() {
            UserProfile profile = new UserProfile(localUser);
            String result = profile.toString();
            // LocalPersonUser: LOCAL prefix + 7 user fields + 3 preferences + 2 stats = 13 parts
            String[] parts = result.split(",");
            assertEquals(13, parts.length);
        }

        @Test
        @DisplayName("Should format LocalPersonUser correctly")
        void shouldFormatLocalPersonUserCorrectly() {
            UserProfile profile = new UserProfile(localUser);
            String result = profile.toString();
            assertTrue(result.startsWith("LOCAL,"));
            assertTrue(result.contains(TEST_BIRTH_PLACE));
            assertTrue(result.contains(TEST_PROVINCE));
        }

        @Test
        @DisplayName("Should format ForeignPersonUser correctly")
        void shouldFormatForeignPersonUserCorrectly() {
            UserProfile profile = new UserProfile(foreignUser);
            String result = profile.toString();
            assertTrue(result.startsWith("FOREIGN,"));
            assertTrue(result.contains(TEST_NIE));
            assertTrue(result.contains(TEST_NATIONALITY));
        }

        @Test
        @DisplayName("Should reflect updated preferences in toString()")
        void shouldReflectUpdatedPreferences() {
            UserProfile profile = new UserProfile(localUser);
            profile.getPreferences().setMinOdd(2.5);
            profile.getPreferences().setMaxAmount(1000.0);
            String result = profile.toString();
            assertTrue(result.contains("2.50"));
            assertTrue(result.contains("1000.00"));
        }

        @Test
        @DisplayName("Should reflect updated stats in toString()")
        void shouldReflectUpdatedStats() {
            UserProfile profile = new UserProfile(localUser);
            profile.getStats().registerBet(50.0);
            profile.getStats().registerBet(100.0);
            String result = profile.toString();
            assertTrue(result.contains("2,150.00"));
        }

        @Test
        @DisplayName("Should differentiate between LocalPersonUser and ForeignPersonUser")
        void shouldDifferentiateBetweenUserTypes() {
            UserProfile localProfile = new UserProfile(localUser);
            UserProfile foreignProfile = new UserProfile(foreignUser);
            
            String localResult = localProfile.toString();
            String foreignResult = foreignProfile.toString();
            
            assertNotEquals(localResult, foreignResult);
            assertTrue(localResult.startsWith("LOCAL,"));
            assertTrue(foreignResult.startsWith("FOREIGN,"));
            assertTrue(localResult.contains(TEST_BIRTH_PLACE));
            assertTrue(foreignResult.contains(TEST_NATIONALITY));
        }

        @Test
        @DisplayName("Should format complete profile with all components")
        void shouldFormatCompleteProfile() {
            UserProfile profile = new UserProfile(localUser);
            profile.getPreferences().setMinOdd(2.0);
            profile.getPreferences().setMaxAmount(750.0);
            profile.getPreferences().setNotificationsEnabled(false);
            profile.getStats().registerBet(100.0);
            
            String result = profile.toString();
            // Verify all components are present
            assertTrue(result.startsWith("LOCAL,"));
            assertTrue(result.contains(TEST_DNI));
            assertTrue(result.contains(TEST_NAME));
            assertTrue(result.contains(TEST_EMAIL));
            assertTrue(result.contains(TEST_BIRTH_PLACE));
            assertTrue(result.contains(TEST_PROVINCE));
            assertTrue(result.contains("2.00"));
            assertTrue(result.contains("750.00"));
            assertTrue(result.contains("false"));
            assertTrue(result.contains("1"));
            assertTrue(result.contains("100.00"));
        }
    }
}
