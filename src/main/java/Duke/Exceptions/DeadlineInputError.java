package Duke.Exceptions;

public class DeadlineInputError extends DukeException {

    public DeadlineInputError() {

        this.ERROR_MESSAGE = "\u2639 OOPS!! deadline command must be in the format: deadline xxx /by yyyy-mm-dd";

    }

}
