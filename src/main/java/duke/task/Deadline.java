package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        super.setTaskType('D');
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String formattedDate = dueDate.format((DateTimeFormatter.ofPattern("MMM d yyyy")));
        return super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String exportAsCSV() {
        return super.exportAsCSV() + "," + dueDate.toString();
    }
}
