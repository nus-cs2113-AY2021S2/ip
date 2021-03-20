package duke.Exceptions;

/**
 * Formatting errors when using <code>DeleteCommand</code>
 */
public class DeleteFormatException extends DukeException {

    public DeleteFormatException() {
        this.errorMessage = "Delete command must be in the format: delete taskIndex";
    }

}
