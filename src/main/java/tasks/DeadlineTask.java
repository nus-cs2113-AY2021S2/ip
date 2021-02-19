package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private LocalDate dueDate;

    public DeadlineTask(String name, LocalDate dueDate) {
        super(name);
        setDueDate(dueDate);
    }

    /**
     * Changes due date.
     *
     * @param dueDate New due date.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Prints task status.
     */
    @Override
    public void printStatus() {
        String formattedDate = dueDate.format(DateTimeFormatter.ofPattern(constants.DATE_PRINT_FORMAT));
        System.out.print("[D]");
        super.printStatus();
        System.out.print(" (by: " + formattedDate + ")");
    }

    /**
     * Outputs formatted data for saving.
     */
    @Override
    public String formatData() {
        String formattedDate = dueDate.format(DateTimeFormatter.ofPattern(constants.DATE_IO_FORMAT));
        return constants.COMMAND_DEADLINE + getName() + constants.KEYWORD_BY + formattedDate + "\n"
                + getDone() + "\n";
    }
}
