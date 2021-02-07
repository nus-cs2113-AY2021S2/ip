package duke.exception;

public class MissingDueDateException extends Exception {
    @Override
    public String getMessage() {
        return "Hmm...have you input the due date for the deadline?";
    }
}
