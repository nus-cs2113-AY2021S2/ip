package duke.task;

public class Deadline extends Task {

    protected String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description, 'D');
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate + ")";
    }
}