package duke.task;

public class Deadline extends duke.task.Task {

    protected String dueDate;

    public Deadline(String description, boolean isDone, String dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + dueDate + ")";
    }

    /**
     * Describes event to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    @Override
    public String saveToHardDisk() {
        return "D" + super.saveToHardDisk() + " | " + dueDate;
    }
}
