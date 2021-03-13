package duke.exception;

/**
 * Represents the Exception when user does not provide a command argument when exepcted to.
 */
public class EmptyCommandArgException extends Exception {
    private String errorMessage;
    private String commandType;

    public EmptyCommandArgException(String commandType) {
        this.commandType = commandType;
    }

    public String getMessage() {
        errorMessage = "Command type of '" + this.commandType + "' must have a description! ):";
        return errorMessage;
    }
    
}
