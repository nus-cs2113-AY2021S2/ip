package duke;

public class Deadline extends Task{
    String by;

    public Deadline(String description, char taskType, String by) {
        super(description);
        this.taskType = taskType;
        this.by = by;
    }

    public String getBy() {return by;}

    /**
     * formulate the complete deadline task Description in task list
     * @return the task type, status and description in the task list
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
