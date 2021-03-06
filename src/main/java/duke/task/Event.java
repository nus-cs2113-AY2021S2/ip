package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents an event task.
 */
public class Event extends Task {
    private LocalDateTime dateTime;

    /**
     * Constructor for Event. All events require a date and time.
     * @param description the description of the task
     * @param dateTime a LocalDateTime object with the date and time of the event
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Alternative constructor for Event. Creates Event from existing information.
     * @param description the description of the event
     * @param isDone the completion status of the event
     * @param dateTime a LocalDateTime object with the date and time of the event
     */
    public Event(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * @return date & time of event
     */
    public LocalDateTime getDatetime() {
        return dateTime;
    }

    /**
     * @return string for command line output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a")) + ")";
    }

    /**
     * @return string for file output
     */
    @Override
    public String toFileOutput() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
