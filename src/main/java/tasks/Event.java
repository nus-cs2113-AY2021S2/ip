package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class Event extends Task {
    LocalDate at = null;
    String atString = "";

    public Event(String description, String at) {
        super(description);
        this.atString = at;
    }
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        if (at != null) {
            atString = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return String.format("[E][" + getStatusIcon() + "] " + description + " (at: " + atString + ")");
    }
}
