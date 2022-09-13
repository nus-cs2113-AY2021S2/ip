package duke.task;

/**
 * Deadline class - object corresponds to a Deadline task represented by name and time
 */
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
        return " (By: " + deadlineTime + ")";
    }

    @Override
    public String outputData() {
        return "[" + getStatusIcon() + "] " + "deadline " + getName() + " by " + deadlineTime;
    }
}