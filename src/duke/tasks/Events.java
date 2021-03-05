package duke.tasks;

/**
 * Represents a event Task
 */
public class Events extends Task {

    private final String at;

    /**
     * @param task contains the task
     * @param dateTimeLocation contains the date/time/location
     */
    public Events(String task, String dateTimeLocation) {
        super(task);
        this.at = dateTimeLocation;
    }

    /** Prints the task with its current status */
    @Override
    public void printStatus() {
        System.out.print("[E]");
        super.printStatus();
        System.out.println("(at: " + at + ")");
    }
}
