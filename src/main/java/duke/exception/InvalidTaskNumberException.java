package duke.exception;

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
