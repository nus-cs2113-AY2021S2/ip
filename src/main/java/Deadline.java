public class Deadline extends Task{
    protected String dueTime;

    public Deadline(String label, String dueTime) {
        super(label);
        this.dueTime = dueTime;
    }

    public String getDueTime() {
        return dueTime;
    }

    @Override
    public String getTypeString() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueTime + ")";
    }
}
