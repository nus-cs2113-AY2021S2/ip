package duke.task;

/**
 * Implements the Event class.
 *
 * @author Leonardo Irvin Pratama
 */
public class Event extends duke.task.Task {

    protected String date;

    public Event(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.getDescription() + " (at: " + date + ")";
    }

    /**
     * Describes event to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    @Override
    public String saveToHardDisk() {
        return "E" + super.saveToHardDisk() + " | " + date;
    }
}
