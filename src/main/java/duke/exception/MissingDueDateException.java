package duke.exception;

/**
 * This exception is thrown when a deadline is created without a valid due date
 */
public class MissingDueDateException extends Exception {
    @Override
    public String getMessage() {
        return "Hmm...have you input the due date for the deadline?";
    }
}
