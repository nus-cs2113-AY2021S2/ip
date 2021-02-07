package duke.exception;

public class MissingTaskDescriptionException extends Exception {
    @Override
    public String getMessage() {
        return "Hmm...have you input the description of the task?";
    }
}
