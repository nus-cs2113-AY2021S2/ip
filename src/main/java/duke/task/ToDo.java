package duke.task;

/**
 * Representation of a type of task with only the description.
 */
public class ToDo extends Task {

    /**
     * Creates a Todo task with description only.
     *
     * @param description description of a task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }
}
