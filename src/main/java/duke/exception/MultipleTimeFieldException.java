package duke.exception;

public class MultipleTimeFieldException extends DukeException {
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public MultipleTimeFieldException() {
        super("Too many time fields detected!\n\tPlease input valid description!\n"
                + "\tTo view an example, type 'help'!\n");
    }
}
