package Task;

/**
 *  Keeps track of a Task that has a due time
 */

public class Deadline extends Task{
    protected String dueTime;

    public Deadline(String label, String dueTime) {
        super(label);
        this.dueTime = dueTime;
    }

    @Override
    public String getTime() {
        return dueTime;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueTime + ")";
    }
}
