package Duke.Exceptions;

public abstract class DukeException extends Exception {

    protected String ERROR_MESSAGE;

    public String getMessage() {
        return this.ERROR_MESSAGE;
    }

}
