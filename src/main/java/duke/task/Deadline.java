package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@inheritDoc}
 * The difference between the Task class and the Deadline class is that the Deadline class
 * keeps track of an additional parameter, the date when the task is due.
 */
public class Deadline extends Task{
    private LocalDate dueDate;

    /**
     * Creates a new Deadline object with the specified description and due date.
     *
     * @param description String containing the description of the Deadline to be created
     * @param dueDate Due date for the Deadline object
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        super.setTaskType('D');
        this.dueDate = dueDate;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public String toString() {
        String formattedDate = dueDate.format((DateTimeFormatter.ofPattern("MMM d yyyy")));
        return super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    /**
     * {@inheritDoc}
     * In addition, it will also export the due date as part of the CSV-formatted string
     */
    public String exportAsCSV() {
        return super.exportAsCSV() + "," + dueDate.toString();
    }
}
