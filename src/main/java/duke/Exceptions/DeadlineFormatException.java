package duke.Exceptions;

/**
 * Formatting errors when using <code>DeadlineCommand</code>
 */
public class DeadlineFormatException extends DukeException {

    public DeadlineFormatException() {
        this.errorMessage = "Deadline command must be in the format: deadline name /by yyyy-mm-dd hhmm";
    }

}
