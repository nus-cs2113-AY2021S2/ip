package duke;

public class InvalidFieldException extends Exception {
    public InvalidFieldException() {
        super();
    }

    public InvalidFieldException(String message) {
        super(message);
    }
}
