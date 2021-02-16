public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String desc, String dueDate) {
        super(desc);
        this.dueDate = dueDate;
    }

    public Deadline(String desc, boolean isDone, String dueDate) {
        super(desc, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
