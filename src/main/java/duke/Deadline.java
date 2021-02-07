package duke;

public class Deadline extends Task{
    String by;

    public Deadline(String description, char taskType, String by) {
        super(description);
        this.taskType = taskType;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
