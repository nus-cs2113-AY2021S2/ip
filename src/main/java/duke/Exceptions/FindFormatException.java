package duke.Exceptions;

/**
 * Formatting errors when using <code>FindCommand</code>
 */
public class FindFormatException extends DukeException {

    public FindFormatException() {
        this.errorMessage = "Find command must be in format: find keyword(s)";
    }
}
