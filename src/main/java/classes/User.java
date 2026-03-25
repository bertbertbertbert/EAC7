package classes;


/**
 * Abstract base class representing a user in the betting system.
 * This class defines the common attributes and behavior for different types of users.
 * Local persons (Spanish citizens) have a DNI, while foreign persons have a NIE.
 * All users must have a valid identification number according to their type.
 * 
 * @author IOC
 * @version 1.0
 */
public abstract class User implements Identificable {

    /** The identification number (DNI or NIE) for the user */

    /** The name of the user */

    /** The email address of the user */

    /** The last name of the user */

    /** The phone number of the user */

    
    /**
     * Constructs a User with the specified identification number, name, email, last name, and phone number.
     * The ID is validated and converted to uppercase. Subclasses must implement the specific validation rules.
     * 
     * @param id the identification number (DNI or NIE) to validate
     * @param name the name of the user
     * @param email the email address of the user
     * @param lastName the last name of the user
     * @param phoneNumber the phone number of the user
     * @throws IllegalArgumentException if any parameter is null, blank, or if ID fails validation for the user type
     */
    protected User(String id, String name, String email, String lastName, String phoneNumber) {

    }

    /**
     * Retrieves the identification number of the user.
     * 
     * @return the user's ID (DNI or NIE)
     */
    @Override
    public String getId() {

    }

    /**
     * Retrieves the name of the user.
     * 
     * @return the user's name
     */
    public String getName() {

    }

    /**
     * Retrieves the email address of the user.
     * 
     * @return the user's email address
     */
    public String getEmail() {

    }

    /**
     * Sets the identification number of the user.
     * The ID is validated and converted to uppercase before assignment.
     * 
     * @param id the new identification number to set
     * @throws IllegalArgumentException if the ID is null, blank, or fails validation for the user type
     */
    public void setId(String id) {

    }

    /**
     * Sets the name of the user.
     * 
     * @param name the new name to set
     * @throws IllegalArgumentException if the name is null or blank
     */
    public void setName(String name) {

    }

    /**
     * Sets the email address of the user.
     * 
     * @param email the new email address to set
     * @throws IllegalArgumentException if the email is null or blank
     */
    public void setEmail(String email) {

    }

    /**
     * Retrieves the last name of the user.
     * 
     * @return the user's last name
     */
    public String getLastName() {

    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName the new last name to set
     * @throws IllegalArgumentException if the last name is null or blank
     */
    public void setLastName(String lastName) {

    }

    /**
     * Retrieves the phone number of the user.
     * 
     * @return the user's phone number
     */
    public String getPhoneNumber() {

    }

    /**
     * Sets the phone number of the user.
     * 
     * @param phoneNumber the new phone number to set
     * @throws IllegalArgumentException if the phone number is null or blank
     */
    public void setPhoneNumber(String phoneNumber) {

    }

    /**
     * Prints the user information in a comma-separated format.
     * @return a string representation of the user like "ID,Name,LastName,Email,PhoneNumber"
     * 
     */
    @Override
    public String toString() {

    }
}
