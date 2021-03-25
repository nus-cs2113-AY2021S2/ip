package duke.exception;

/**
 * Signals an error occur when user entered an empty string.
 */
public class InvalidInputException extends Exception {
    public String errorMessage = "☹ OOPS!!! Sorry I don't understand what you mean.\n";

    @Override
    public String getMessage() {
        return errorMessage;
    }

}
