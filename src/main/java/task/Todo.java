package duke.task;

public class Todo extends duke.task.Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }

    /**
     * Describes event to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    @Override
    public String saveToHardDisk() {
        return "T" + super.saveToHardDisk();
    }
}
