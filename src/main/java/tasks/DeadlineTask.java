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

    /**
     * Outputs formatted data for saving.
     */
    @Override
    public String formatData() {
        return "deadline " + getName() + " /by " + dueDate + "\n"
                + getDone() + "\n";
    }
}
