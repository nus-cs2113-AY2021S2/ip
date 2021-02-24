package duke.exception;

/**
 * Represents the Exception when the user does not provide a valid task number.
 * This applies to the 'done' and 'delete' command.
 */
public class InvalidTaskNumberException extends Exception {
    private static String errorMessage = " is not a valid task number.";
    private String input;

    public InvalidTaskNumberException(String input) {
        this.input = input;
    }

    public InvalidTaskNumberException(int input) {
        this.input = Integer.toString(input);
    }

    public String getMessage() {
        return "'" + this.input + "'" + errorMessage;
    }
}
