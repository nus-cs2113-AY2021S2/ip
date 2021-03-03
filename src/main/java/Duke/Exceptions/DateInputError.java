package Duke.Exceptions;

public class DateInputError extends DukeException {

    /**
     * Constructor for the error message
     */
    public DateInputError() {

        this.errorMessage = "\u2639 OOPS!! date command must be in the format: \ndate yyyy-mm-dd";

    }

}
