package duke.exception;

/**
 * InvalidCommandException is raised when the user provides an invalid input.
 */
public class InvalidCommandException extends DukeException {
    private static final String MESSAGE = "I'm sorry, but I don't know what that means :-(";

    public InvalidCommandException() {
    }

    public String getMessage() {
        return MESSAGE;
    }
}
