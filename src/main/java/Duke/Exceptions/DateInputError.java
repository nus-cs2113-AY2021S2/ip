package Duke.Exceptions;

public class DateInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public DateInputError() {

        this.errorMessage = "OOPS!! date command must be in the format: \ndate yyyy-mm-dd";

    }

}
