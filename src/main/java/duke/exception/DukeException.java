package duke.exception;

public abstract class DukeException extends Exception {

    protected String ERROR_MESSAGE;

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
