package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@inheritDoc}
 * The difference between the Task class and the Event class is that the Event class
 * keeps track of an additional parameter, the date when the event occurs.
 */
public class Event extends Task {
    private LocalDate eventDate;

    /**
     * Creates a new Event object with the specified description and event date.
     *
     * @param description a description for the task to be added
     * @param eventDate the date when the event occurs
     */
    public Event(String description, LocalDate eventDate) {
        super(description);
        super.setTaskType('E');
        this.eventDate = eventDate;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public String toString() {
        String formattedDate = eventDate.format((DateTimeFormatter.ofPattern("MMM d yyyy")));
        return super.toString() + " (on: " + formattedDate + ")";
    }

    @Override
    /**
     * {@inheritDoc}
     * In addition, it will also export the event date as part of the CSV-formatted string
     */
    public String exportAsCSV() {
        return super.exportAsCSV() + "," + eventDate.toString();
    }
}
