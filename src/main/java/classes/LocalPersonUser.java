package classes;


/**
 * Represents a natural person user with a validated DNI (Spanish national identification number).
 * This class extends User and provides validation for the DNI format according to Spanish regulations.
 * 
 * @author IOC
 * @version 1.0
 */
public class LocalPersonUser extends User {

    // The birth place of the local person user

    // The province of the local person user

    
    /**
     * Constructs a LocalPersonUser with the specified DNI, name, email, last name, phone number, birth place, and province.
     * 
     * @param dni the DNI (national identification number) to validate
     * @param name the first name of the natural person
     * @param email the email address of the natural person
     * @param lastName the last name of the natural person
     * @param phoneNumber the phone number of the natural person
     * @param birthPlace the city or town of birth
     * @param province the province of residence
     * @throws IllegalArgumentException if any parameter is null, blank, or if DNI is invalid
     */
    public LocalPersonUser(String dni, String name, String email, String lastName, String phoneNumber, String birthPlace, String province) {

    }

    /**
     * Retrieves the birth place of the local person user.
     * 
     * @return the birth place
     */
    public String getBirthPlace() {

    }

    /**
     * Sets the birth place of the local person user.
     * 
     * @param birthPlace the birth place to set
     * @throws IllegalArgumentException if birthPlace is null or blank
     */
    public void setBirthPlace(String birthPlace) {

    }

    /**
     * Retrieves the province of the local person user.
     * 
     * @return the province
     */
    public String getProvince() {

    }

    /**
     * Sets the province of the local person user.
     * 
     * @param province the province to set
     * @throws IllegalArgumentException if province is null or blank
     */
    public void setProvince(String province) {

    }

    /**
     * Validates the DNI for a natural person according to Spanish regulations.
     * The DNI must consist of 8 digits followed by a check letter.
     * The check letter is calculated based on the numeric value modulo 23.
     * Can find more info here https://medium.com/@manuelmato/c%C3%B3mo-validar-un-dni-en-java-6d7ce7d764aa
     * 
     * @return true if the DNI is valid for a natural person, false otherwise (including invalid format)
     */
    @Override
    public boolean hasValidId() {

    }

    /**
     * Returns a string representation of the LocalPersonUser.
     * 
     * @return a string containing user details including birth place and province
     */
    @Override
    public String toString() {

    }
}

