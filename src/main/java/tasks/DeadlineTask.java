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
        return constants.COMMAND_DEADLINE + getName() + constants.KEYWORD_BY + dueDate + "\n"
                + getDone() + "\n";
    }
}
