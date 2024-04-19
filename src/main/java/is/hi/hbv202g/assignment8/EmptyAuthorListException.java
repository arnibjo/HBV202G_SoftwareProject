package is.hi.hbv202g.assignment8;

/**
 * Represents an exception that occurs when an author list is empty.
 */
public class EmptyAuthorListException extends Exception {

    /**
     * Constructs a new exception with the specified message.
     *
     * @param message the detail message
     */
    public EmptyAuthorListException(String message) {
        super(message);
    }
}
