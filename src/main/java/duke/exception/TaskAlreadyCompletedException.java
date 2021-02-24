package duke.exception;

/**
 * This exception is thrown when a user tries to mark an already completed task as complete
 */
public class TaskAlreadyCompletedException extends Exception {
    @Override
    public String getMessage() {
        return "You have already marked this task as complete! Please choose an incomplete task.";
    }
}
