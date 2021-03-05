package Duke;

public class TodoException extends Exception {
    private final String errorMessage;

    public TodoException() {
        this.errorMessage = "☹ OOPS!!! The description of the task cannot be empty.";
    }

    public String sendErrorMessage() {
        return this.errorMessage;
    }

}


