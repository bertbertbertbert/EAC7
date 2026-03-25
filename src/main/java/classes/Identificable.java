package classes;
/**
 * Interface for entities that can be identified by a unique identifier.
 * Implementations should provide a mechanism to retrieve and validate
 * identification numbers such as DNI (Spanish National Identity Document)
 * or NIE (Foreigner's Identity Number).
 */
public interface Identificable {

    /**
     * Returns the unique identifier (DNI/NIE) of the entity.
     *
     * @return the identification string (DNI or NIE)
     */

    /**
     * Checks if the current identifier is valid (DNI or NIE format).
     *
     * @return {@code true} if the identifier is valid, {@code false} otherwise
     */
    
}
