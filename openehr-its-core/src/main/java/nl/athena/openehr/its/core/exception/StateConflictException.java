package nl.athena.openehr.its.core.exception;

/**
 * Exception thrown when a state conflict occurs.
 */
public class StateConflictException extends RuntimeException {

    /**
     * Creates a new instance of this exception with the given message.
     *
     * @param theMessage The message of the exception.
     */
    public StateConflictException(final String theMessage) {
        super(theMessage);
    }

    /**
     * Creates a new instance of this exception with the given message and cause.
     *
     * @param theMessage The message of the exception.
     * @param theCause   The cause of the exception.
     */
    public StateConflictException(
            final String theMessage,
            final Throwable theCause) {
        super(theMessage, theCause);
    }

}
