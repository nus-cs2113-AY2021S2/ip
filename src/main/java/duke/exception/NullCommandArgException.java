package duke.exception;

public class NullCommandArgException extends Exception {
    private String errorMessage;
    private String commandType;

    public NullCommandArgException(String commandType) {
        this.commandType = commandType;
    }

    public String getMessage() {
        errorMessage = "ERROR!!! Command type of '" + this.commandType + "' must have a description! ):";
        return errorMessage;
    }
    
}
