package tasks;

import common.Constants;

public class DeadlineTask extends Task {

    private String dueDate;
    private static final Constants constants = new Constants();

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
        return "deadline " + getName() + constants.KEYWORD_BY + dueDate + "\n"
                + getDone() + "\n";
    }
}
