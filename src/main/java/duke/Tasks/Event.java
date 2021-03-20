package duke.Tasks;

import java.time.LocalDateTime;

/**
 * Extending the <code>Task</code> class that includes a <code>LocalDateTime</code> Event at
 */
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
