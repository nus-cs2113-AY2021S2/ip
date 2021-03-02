package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a deadline class. Deadline inherits task, in which the user can input and indicated by deadline TASKNAME /by dd-MM-yyyy HH:mm.
 */
public class Deadline extends Task {
    /**
     * Date and time of deadline in string format.
     */
    protected String byDate;
    /**
     * Date and time of deadline in DateTime format.
     */
    protected LocalDateTime dateTime;

    /**
     * Creates an deadline task object to be added into the task list.
     * @param description Deadline description.
     * @param by Date and time which deadline is by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDate = by;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(by, dateTimeFormat);
    }

    /**
     * Gets date and time of deadline in string format.
     * @return Date and time.
     */
    public String getByDate() {
        return byDate;
    }

    /**
     * Gets date and time of deadline in DateTime format.
     * @return Date and time.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Prints details of deadline.
     * @return Task type D, description, and date time.
     */
    public String printDescription() {
        return "[D]" + super.printDescription() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }
}