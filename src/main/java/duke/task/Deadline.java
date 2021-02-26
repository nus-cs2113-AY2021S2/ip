package duke.task;

/**
 * Represent a deadline that is a specific type of task.
 */
public class Deadline extends Task {
    protected String date;

    public Deadline (String description, String date) {
        super(description);
        this.date = date;
        this.taskType = "[D]";
    }

    @Override
    public String getDate () {
        return date;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + date + ")";
    }

}
