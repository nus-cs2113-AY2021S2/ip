package tasks;

/**
 * Represents a event Task
 */
public class Events extends Task {

    private final String at;

    /**
     * @param task contains the task
     * @param dateAndTime contains the date and time
     */
    public Events(String task, String dateAndTime) {
        super(task);
        this.at = dateAndTime;
    }

    /** Prints the task with its current status */
    @Override
    public void printStatus() {
        System.out.print("[E]");
        super.printStatus();
        System.out.println("(at: " + at + ")");
    }
}
