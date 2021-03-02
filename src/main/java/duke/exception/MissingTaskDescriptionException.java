package duke.exception;

/**
 * This exception is called when the user attempts to create a task without inputting a description
 */
public class MissingTaskDescriptionException extends Exception {
    @Override
    public String getMessage() {
        return "Hmm...have you input the description of the task?";
    }
}
