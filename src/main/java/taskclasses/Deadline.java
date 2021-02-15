package taskclasses;

public class Deadline extends Task {
    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[" + getTaskType() + "]" + super.toString() + " (by: " + getTime() + ")");
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public String getTime() {
        return this.by;
    }
}
