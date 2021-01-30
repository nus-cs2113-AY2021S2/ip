public class Deadline extends Task{
    String by;
    protected boolean isDone;

    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
