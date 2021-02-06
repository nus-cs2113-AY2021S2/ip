package tasks;

public class DeadlineTask extends Task {

    private String dueDate;

    public DeadlineTask(String name, String dueDate) {
        super(name);
        setDueDate(dueDate);
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Prints task status.
     */
    @Override
    public void printStatus() {
        System.out.print("[D]");
        super.printStatus();
        System.out.print(" (by: " + dueDate + ")");
    }
}
