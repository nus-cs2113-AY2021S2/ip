package duke.task;

/**
 * Represent a todo that is a specific type of task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.taskType = "[T]";
    }

}
