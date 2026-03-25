package classes;

/**
 * Represents a foreign person user with a validated NIE (Spanish foreign identification number).
 * This class extends User and ensures that the NIE follows the validation rules for foreign persons.
 * 
 * @author IOC
 * @version 1.0
 */
public class ForeignPersonUser extends User {

    // The nationality of the foreign person user

    
    /**
     * Constructs a ForeignPersonUser with the specified NIE, name, email, last name, phone number, and nationality.
     * 
     * @param nie the NIE (foreign identification number) to validate
     * @param name the name of the foreign person
     * @param email the email address of the foreign person
     * @param lastName the last name of the foreign person
     * @param phoneNumber the phone number of the foreign person
     * @param nationality the country of citizenship
     * @throws IllegalArgumentException if any parameter is null, blank, or if NIE is invalid
     */
    public ForeignPersonUser(String nie, String name, String email, String lastName, String phoneNumber, String nationality) {

    }

    /**
     * Retrieves the nationality of the foreign person user.
     * 
     * @return the nationality
     */
    public String getNationality() {

    }

    /**
     * Sets the nationality of the foreign person user.
     * 
     * @param nationality the nationality to set
     * @throws IllegalArgumentException if nationality is null or blank
     */
    public void setNationality(String nationality) {

    }

    /**
     * Validates the NIE for a foreign person according to Spanish regulations.
     * The NIE must start with 'X', 'Y', or 'Z', followed by 7 digits and a check letter.
     * The check letter is calculated based on the numeric value modulo 23.
     * More info can be found here https://medium.com/@manuelmato/c%C3%B3mo-validar-un-nie-en-java-e1aa57e5745c
     * 
     * @return true if the NIE is valid for a foreign person, false otherwise (including invalid format)
     */
    @Override
    public boolean hasValidId() {

    }

    /**
     * Prints the foreign person user information in a comma-separated format.
     * @return a string representation of the foreign person user like "ID,Name,LastName,Email,PhoneNumber, Nationality"
     * 
     */
    @Override
        public String toString() {

        }
}