package duke.Tasks;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, inputFormatter);
    }

    public Event(String description, String at, boolean loadFlag) {
        super(description);
        this.at = LocalDateTime.parse(at, outputFormatter);
    }

    public LocalDateTime getDateTime() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(outputFormatter) + ")";
    }
}
