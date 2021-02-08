package duke.exception;

public class InvalidCommandTimeException extends Exception {
    private String errorMessage;
    private String commandType;

    public InvalidCommandTimeException(String commandType) {
        this.commandType = commandType;
    }

    public String getMessage() {
        String timeCommand = null;
        switch (this.commandType) {
        case "deadline":
            timeCommand = "'/by'";
            break;
        case "event":
            timeCommand = "'/at'";
            break;
        }
        errorMessage = "ERROR!!! Command type of \'" + this.commandType + "\' must define a time using " + timeCommand + "! ):";
        return errorMessage;
    }
}
