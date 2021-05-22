package Duke.Exceptions;

public class DeadlineInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public DeadlineInputError() {

        this.errorMessage = "OOPS!! deadline command must be in the format: \ndeadline xxx /by yyyy-mm-dd";

    }

}
