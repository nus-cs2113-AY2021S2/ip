package duke.data.task;

import java.time.LocalDateTime;

public abstract class TaskWithDateTime extends Task {
    protected static final String FILE_OUTPUT_STRING_FORMAT = "%c %s | %s";
    protected boolean hasDateTime;
    protected LocalDateTime dateTime;

    public TaskWithDateTime(String description) {
        this(description, false);
    }

    public TaskWithDateTime(String description, boolean isDone) {
        super(description, isDone);
        hasDateTime = false;
        dateTime = null;
    }

    public boolean hasDateTime() {
        return hasDateTime;
    }

    public void setHasDateTime(boolean hasDateTime) {
        this.hasDateTime = hasDateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
