package Duke;

public class InvalidCommandException extends Exception {
    private String errorMessage;

    public InvalidCommandException() {
        this.errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public String sendErrorMessage() {
        return this.errorMessage;
    }
}
