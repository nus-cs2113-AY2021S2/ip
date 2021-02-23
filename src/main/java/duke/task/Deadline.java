package duke.task;

public class Deadline extends Task {

    protected String deadlineTime;

    public Deadline(String description, String by) {
        super(description);
        this.deadlineTime = by;
    }

    @Override
    public String getType(){
        return "D";
    }

    @Override
    public String getDate() {
        return " (by: " + deadlineTime + ")";
    }

    @Override
    public String outputData() {
        return "[" + getStatusIcon() + "] " + "deadline " + getName() + " /by " + deadlineTime;
    }
}