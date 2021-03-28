package duke.task;

import java.time.LocalDate;

/**
 * Represents a {@code Todo} object, which is inherited from {@code Task}. A {@code Todo} object is initialized
 * with a task name and provide methods for returning a string of the object in displaying/saving format.
 */
public class Todo extends Task {

    public static final String TASK_TYPE = "T";

    /**
     * Constructor of {@code Todo}<br>
     * Initializes the {@code Todo} object with the given parameter.
     *
     * @param taskName name of the {@code Todo} Object
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns null since {@code Todo} object does not have a date/deadline.
     *
     * @return null
     */
    @Override
    public LocalDate getDate() {
        return null;
    }

    /**
     * Returns a string of all information about the {@code Todo} object for displaying in Command-Line Interface.
     *
     * @return A string of information about the {@code Todo} object for CLI display
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }

    /**
     * Returns a string of all information about the {@code Todo} object for storage in text file.
     *
     * @return A string of information about the {@code Todo} object for saving
     */
    @Override
    public String toSave() {
        return TASK_TYPE + super.toSave();
    }
}
