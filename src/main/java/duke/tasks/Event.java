package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents an event class. Event inherits task, in which the user can input and indicated by event TASKNAME /at dd-MM-yyyy HH:mm.
 */
public class Event extends Task {
    /**
     * Date and time of event in string format.
     */
    protected String atDate;
    /**
     * Date and time of event in DateTime format.
     */
    protected LocalDateTime dateTime;

    /**
     * Creates an Event task object to be added into the task list.
     * @param description Event task description.
     * @param at Date and time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.atDate = at;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(at, dateTimeFormat);
    }

    /**
     * Gets date and time of event in string format.
     * @return Date and time.
     */
    public String getAtDate() {
        return atDate;
    }

    /**
     * Gets date amd time in DateTime format.
     * @return Date and time.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Prints details of event.
     * @return Task type E, description, and date time.
     */
    public String printDescription() {
        return "[E]" + super.printDescription() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }
}