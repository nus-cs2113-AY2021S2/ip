package duke.exception;

public class InvalidInputException extends Exception {
    @Override
    public String getMessage() {
        return "I don't understand...please enter a valid command";
    }
}

