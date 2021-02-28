package duke.Exceptions;

/**
 * Formatting errors when using <code>AddCommand</code>
 */
public class AddFormatException extends DukeException {

    public AddFormatException(String command) {
        this.errorMessage = "Please enter the description of " + command;
    }
}
