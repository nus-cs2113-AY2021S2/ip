public class Deadline extends Task{
    String by;
    protected boolean isDone;

    public Deadline(String description, char taskType, String by) {
        super(description);
        this.taskType = taskType;
        this.by = by;
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
