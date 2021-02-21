package duke.taskActions;

import java.time.DateTimeException;

import duke.data.Deadline;
import duke.data.exception.DukeException;
import duke.data.Event;
import duke.data.Todo;
import duke.data.exception.DukeExceptionKey;

public class TaskGenerator {

    /**
     * Generates a new Todo task.
     * @param details Name of Todo task.
     * @return An undone Todo task.
     * @throws DukeException When task name is not given.
     */
    public static Todo createTodo(String details) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException(DukeExceptionKey.EMPTY_TODO);
        }
        return new Todo(details, false);
    }

    /**
     * Generates a Deadline task.
     * @param details Name of Deadline task.
     * @return An undone Deadline task.
     * @throws DukeException When task name or deadline not given.
     */
    public static Deadline createDeadline(String details) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException(DukeExceptionKey.EMPTY_DEADLINE);
        }
        try {
            String[] deadlineTask = details.split(" /by ");
            String description = deadlineTask[0].trim();
            String deadline = DeriveDateTime.deriveDateTime(deadlineTask[1].trim());
            return new Deadline(description, false, deadline);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(DukeExceptionKey.EMPTY_DEADLINE_TIME);
        } catch (DateTimeException dateTimeException) {
            throw new DukeException(DukeExceptionKey.INVALID_DATETIME);
        }
    }

    /**
     * Generates an Event task.
     * @param details Name of Event task.
     * @return An undone Event task.
     * @throws DukeException When task name or event not given.
     */
    public static Event createEvent(String details) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException(DukeExceptionKey.EMPTY_EVENT);
        }
        try {
            String[] eventTask = details.split(" /at ");
            String description = eventTask[0].trim();
            String event = DeriveDateTime.deriveDateTime(eventTask[1].trim());
            return new Event(description, false, event);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(DukeExceptionKey.EMPTY_EVENT_TIME);
        } catch (DateTimeException dateTimeException) {
            throw new DukeException(DukeExceptionKey.INVALID_DATETIME);
        }
    }
}
