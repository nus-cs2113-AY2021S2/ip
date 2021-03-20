package duke.Exceptions;

/**
 * The class <code>DukeException</code> encompasses errors that could occur in the Duke program
 */
public class DukeException extends Exception {
    protected String errorMessage;

    public String getMessage() {
        return errorMessage;
    }
}
