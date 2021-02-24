package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventDate;

    public Event(String description, LocalDate eventDate) {
        super(description);
        super.setTaskType('E');
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        String formattedDate = eventDate.format((DateTimeFormatter.ofPattern("MMM d yyyy")));
        return super.toString() + " (on: " + formattedDate + ")";
    }

    @Override
    public String exportAsCSV() {
        return super.exportAsCSV() + "," + eventDate.toString();
    }
}
