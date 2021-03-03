package Duke.Exceptions;

public class TodoInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public TodoInputError() {

        this.errorMessage = "\u2639 OOPS!! todo command must be in the format: \ntodo xxx";

    }

}
