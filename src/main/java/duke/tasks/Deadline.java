package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String item, LocalDateTime deadline) {
        super(item);
        this.deadline = deadline;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.print(" (by: " + this.getFormattedDeadline() + ")");
    }

    @Override
    public String toString() {
        return super.toString() + " | "
                + this.getDeadline();
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getFormattedDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h.mm a"));
    }
}
