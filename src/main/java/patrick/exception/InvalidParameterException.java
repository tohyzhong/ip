package patrick.exception;

/**
 * Thrown to indicate that a command has been provided with illegal or missing
 * arguments.
 */
public class InvalidParameterException extends PatrickException {
    public InvalidParameterException(String message) {
        super(message);
    }
}
