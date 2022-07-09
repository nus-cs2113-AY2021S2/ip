package duke.exception;

public class InvalidTaskDateTimeException extends DukeException {
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public InvalidTaskDateTimeException() {
        super("Please input the correct date and/or time format!\n"
                + "\tTo view an example, type 'help'!\n");
    }
}
