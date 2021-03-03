package Duke.Exceptions;

public class DeadlineInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public DeadlineInputError() {

        this.errorMessage = "\u2639 OOPS!! deadline command must be in the format: \ndeadline xxx /by yyyy-mm-dd";

    }

}
