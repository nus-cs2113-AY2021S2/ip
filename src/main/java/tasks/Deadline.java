package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String byString = "";
    LocalDate by = null;

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        if (by != null) {
            byString = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return String.format("[D][" + getStatusIcon() + "] " + description + " (by: " + byString + ")");
    }
}
