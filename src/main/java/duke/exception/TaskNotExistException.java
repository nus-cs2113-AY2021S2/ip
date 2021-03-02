package duke.exception;

/**
 * This exception is thrown when the user attempts to select a task that is out of range of the displayed tasks
 */
public class TaskNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "This task doesn't exist! Please choose a valid task!";
    }
}
