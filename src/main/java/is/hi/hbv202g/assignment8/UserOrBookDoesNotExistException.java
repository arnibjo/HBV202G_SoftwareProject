package is.hi.hbv202g.assignment8;

/**
 * Represents an exception that occurs when a user or book does not exist.
 */
public class UserOrBookDoesNotExistException extends Exception {
    
    /**
     * Constructs a new exception with the specified message.
     *
     * @param message the detail message
     */
    public UserOrBookDoesNotExistException(String message) {
        super(message);
    }
}
