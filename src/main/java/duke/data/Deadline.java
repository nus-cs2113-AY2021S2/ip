package duke.data;

import java.time.DateTimeException;

import duke.data.exception.DukeException;
import duke.data.exception.DukeExceptionKey;
import duke.taskActions.DateTimeFormatter;

public class Deadline implements Task {
    private final String description;
    private final boolean isDone;
    private final String message;
    private final String deadline;

    /**
     * Constructs a Deadline task.
     * @param description Name of task.
     * @param isDone True if this task is completed.
     * @param deadline Date/time this task expires.
     */
    public Deadline(String description, boolean isDone, String deadline) throws DukeException {
        try {
            String time = DateTimeFormatter.deriveDateTime(deadline, false);
            String fullDescription = String.format("%s (by: %s)", description, time);
            this.description = description.trim();
            this.deadline = deadline;
            this.isDone = isDone;
            this.message = String.format(
                    "[D][%s] %s",
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

    public String getDeadline() {
        return deadline;
    }
}
