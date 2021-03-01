package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
	}

    public String printDate() {
        return " (by: " + getDate() + ")";
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    public String getDetails() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription() + printDate();
    }

}
