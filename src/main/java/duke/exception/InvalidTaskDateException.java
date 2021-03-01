package duke.exception;

public class InvalidTaskDateException extends DukeException {
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public InvalidTaskDateException() {
        super("Please input the correct date format!\n"
                + "\tTo view an example, type 'help'!\n");
    }
}
