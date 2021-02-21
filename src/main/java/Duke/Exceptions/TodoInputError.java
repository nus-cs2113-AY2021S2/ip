package Duke.Exceptions;

public class TodoInputError extends DukeException {

    public TodoInputError() {
        this.ERROR_MESSAGE = "\u2639 OOPS!! todo command must be in the format: todo xxx";
    }

}
