package duke.data.exceptions;

/**
 * This exception should only be raised when the user inputs
 * an invalid command. Handler must be able to handle this
 * exception gracefully.
 * Example of invalid command:
 * - "non-command word"
 * - list something
 * - bye bye
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
