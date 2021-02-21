package duke.data;

import java.time.DateTimeException;

import duke.data.exception.DukeException;
import duke.data.exception.DukeExceptionKey;
import duke.taskActions.DateTimeFormatter;

public class Event implements Task {
    private final String description;
    private final boolean isDone;
    private final String message;
    private final String event;

    /**
     * Constructs an Event task.
     * @param description Name of task.
     * @param isDone True if this task is completed.
     * @param event Date/time this task expires.
     */
    public Event(String description, boolean isDone, String event) throws DukeException {
        try {
            String time = DateTimeFormatter.deriveDateTime(event, true);
            String fullDescription = String.format("%s (at: %s)", description, time);
            this.description = description.trim();
            this.event = event;
            this.isDone = isDone;
            this.message = String.format(
                    "[E][%s] %s",
                    isDone ? "X" : " ",
                    fullDescription
            );
        } catch (DateTimeException dateTimeException) {
            throw new DukeException(DukeExceptionKey.INVALID_DATETIME);
        }
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public String getEvent() {
        return event;
    }
}
