package patrick.exception;

/**
 * The base exception class for Patrick.
 * All custom exceptions thrown by Patrick's functionality will extend
 * {@code PatrickException}.
 */
public class PatrickException extends Exception {
    public PatrickException(String message) {
        super(message);
    }
}
