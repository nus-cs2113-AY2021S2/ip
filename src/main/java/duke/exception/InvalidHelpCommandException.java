package duke.exception;

public class InvalidHelpCommandException extends DukeException{
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public InvalidHelpCommandException() {
        super("Do you mean 'help'?\n");
    }
}
