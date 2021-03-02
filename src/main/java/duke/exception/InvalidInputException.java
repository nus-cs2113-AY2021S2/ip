package duke.exception;

/**
 * This is for when a user enters an incorrect input command that is not recognized by the program
 */
public class InvalidInputException extends Exception {
    @Override
    public String getMessage() {
        return "I don't understand...please enter a valid command";
    }
}

