package Duke.Exceptions;

/**
 * Abstract DukeException class to be extended by all other error classes
 */
public abstract class DukeException extends Exception {

    /**
     * errorMessage to be printed in the command line whenever the error is triggered
     */
    protected String errorMessage;

    /**
     *
     * @return the error message for the user
     */
    public String getMessage() {
        return this.errorMessage;
    }

}
