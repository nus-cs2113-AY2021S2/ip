package duke.exception;

public class DukeException extends Exception {
    private TaskType taskType;

    public DukeException(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }
}
