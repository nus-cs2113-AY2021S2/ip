package duke.exception;

/**
 * It is a checked exception that is thrown when the argument provided is invalid.
 */
public class InvalidArgumentException extends Exception {
    String errorMsg;

    public InvalidArgumentException(String errorMsg){
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
