package duke.exception;

/**
 * Exception for invalid user inputs when creating Tasks.
 */
public class InvalidTaskCommandException extends DukeException {
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public InvalidTaskCommandException() {
        super("Please enter a valid command!\n"
                + "\tTo view an example, type 'help'!\n");
    }
}
