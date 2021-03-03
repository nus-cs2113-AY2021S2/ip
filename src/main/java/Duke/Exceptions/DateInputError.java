package Duke.Exceptions;

public class DateInputError extends DukeException {

    public DateInputError() {

        this.ERROR_MESSAGE = "\u2639 OOPS!! date command must be in the format: \ndate yyyy-mm-dd";

    }

}
