package duke.exception;

public class TaskAlreadyCompletedException extends Exception {
    @Override
    public String getMessage() {
        return "You have already marked this task as complete! Please choose an incomplete task.";
    }
}
