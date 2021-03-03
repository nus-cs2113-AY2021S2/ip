package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public LocalDate getAt() {
        return at;
    }

    public void setAt(LocalDate at) {
        this.at = at;
    }

    public Event(String content, LocalDate at) {
        super(content);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String strAddToTxt() { return "E" + super.strAddToTxt() + " | " + at; }
}
