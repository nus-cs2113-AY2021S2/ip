package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class representing the task objects
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public boolean getDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract LocalDateTime getDateTime();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public void setDone() {
        isDone = true;
    }
}
