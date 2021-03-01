package duke.exception;

public class InvalidTaskDescriptionException extends DukeException {
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public InvalidTaskDescriptionException() {
        super("Please input valid description!\n"
                + "\tTo view an example, type 'help'!\n");
    }
}
