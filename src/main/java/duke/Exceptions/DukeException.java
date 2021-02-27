package duke.Exceptions;

public class DukeException extends Exception {
    protected String errorMessage;

    public String getMessage() {
        return errorMessage;
    }
}
