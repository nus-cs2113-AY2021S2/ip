package duke.task;

import java.time.LocalDate;

/**
 * Represents a todo inherited from {@code Task}<br>
 * A {@code Todo} object consists of a parameter:<br>
 * 1. {@code taskName}: name of the Todo object<br>
 */
public class Todo extends Task {

    public static final String TASK_TYPE = "T";

    /**
     * Constructor of Todo<br>
     * Initializes the Todo object with the given parameter
     *
     * @param taskName name of the Todo Object
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns null (It is because Todo objects have no date/time)
     *
     * @return null
     */
    @Override
    public LocalDate getDate() {
        return null;
    }

    /**
     * Returns the task type of the Todo object, i.e. "T"
     *
     * @return Task type of the Todo object
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Returns a string of all information about the Todo object for displaying in Command-Line Interface
     *
     * @return A string of information about the Todo object for CLI display
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }

    /**
     * Returns a string of all information about the Todo object for storage in text file
     *
     * @return A string of information about the Todo object for saving
     */
    @Override
    public String toSave() {
        return TASK_TYPE + super.toSave();
    }
}
