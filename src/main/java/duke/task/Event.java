package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    
    public LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[EV]" + super.toString() + " (at: " + this.getAt() + ")";
    }

    public String getAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String atString = this.at.format(formatter);
        return atString;
    }

    @Override
    public boolean isUrgent() {
        if (this.isDone()) {
            return false;
        }
        LocalDateTime weekDateTime = LocalDateTime.now().plusDays(7);
        return this.at.isBefore(weekDateTime);
    }
}
