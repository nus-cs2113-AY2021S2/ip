package duke.exception;

public class NoTaskException extends DukeException {
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public NoTaskException() {
        super("There are currently no tasks in your list!\n"
                + "\tStart adding to your list!\n");
    }
}
