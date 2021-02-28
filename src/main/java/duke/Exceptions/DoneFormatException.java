package duke.Exceptions;

/**
 * Formatting errors when using <code>DoneCommand</code>
 */
public class DoneFormatException extends DukeException {

    public DoneFormatException() {
        this.errorMessage = "Done command must be in the format: done taskIndex";
    }
}
