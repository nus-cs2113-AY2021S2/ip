package duke.exception;

/**
 * Keeps track of the TaskType causing the exception.
 * Different TaskTypes will have different exception messages.
 */
public class DukeException extends Exception {
    private TaskType taskType;

    public DukeException(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }
}
