package duke.task;

public class Deadline extends Task {
    protected String date;

    public Deadline(String desc, String dueDate) {
        super(desc);
        this.date = dueDate;
    }

    public Deadline(String desc, boolean isDone, String dueDate) {
        super(desc, isDone);
        this.date = dueDate;
    }

    @Override
    public String getDate() {
        return date;
    }

    public String toString() {
        return " [D]" + super.toString() + " (by: " + date + ")";
    }
}
