package duke.task;

import duke.task.dateTime.DateTime;

import java.time.LocalDate;

/**
 * Represents a deadline inherited from {@code Task}<br>
 * A {@code Deadline} object consists of two parameters:<br>
 * 1. {@code taskName}: name of the Deadline object<br>
 * 2. {@code dueDay}: date and time [optional] of the due day
 */
public class Deadline extends Task {

    public static final String TASK_TYPE = "D";

    private final DateTime dueDay;

    /**
     * Constructor of Deadline<br>
     * Initializes the Deadline object with the given parameters
     *
     * @param taskName name of the Deadline Object
     * @param dueDay   deadline of the Deadline Object
     */
    public Deadline(String taskName, String dueDay) {
        super(taskName);
        this.dueDay = new DateTime(dueDay);
    }

    /**
     * Returns the date of deadline of the Deadline object
     *
     * @return date of the Deadline object
     */
    @Override
    public LocalDate getDate() {
        return dueDay.getDate();
    }

    /**
     * Returns the task type of the Deadline object, i.e. "D"
     *
     * @return Task type of the Deadline object
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Returns a string of all information about the Deadline object for displaying in Command-Line Interface
     *
     * @return A string of information about the Deadline object for CLI display
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by: " + dueDay.toString() + ")";
    }

    /**
     * Returns a string of all information about the Deadline object for storage in text file
     *
     * @return A string of information about the Deadline object for saving
     */
    @Override
    public String toSave() {
        return TASK_TYPE + super.toSave() + " /by " + dueDay.toSave();
    }
}
