package duke.exception;

/**
 * Represents the Exception when the user does not provide a valid date and time.
 * This applies to the 'deadline' and 'event' commands.
 */
public class InvalidCommandTimeException extends Exception {
    private String errorMessage;
    private String commandType;
    private String parseErrorMessage;

    public InvalidCommandTimeException(String commandType, Exception e) {
        this.commandType = commandType;
        this.parseErrorMessage = e.getLocalizedMessage();
    }

    public String getMessage() {
        String timeCommand = null;
        switch (this.commandType) {
        case "deadline":
            timeCommand = "/by";
            break;
        case "event":
            timeCommand = "/at";
            break;
        }
        errorMessage = "Command type of \'" + this.commandType + "\' must define a time using:\n   "
                + this.commandType + " taskName " + timeCommand + " dd/mm/yyyy hhmm\n"
                + parseErrorMessage;
        return errorMessage;
    }
}
