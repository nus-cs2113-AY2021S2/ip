package duke.task;

import duke.task.dateTime.DateTime;

import java.time.LocalDate;


/**
 * Represents a {@code Deadline} object, which is inherited from {@code Task}. An {@code Deadline} object is initialized
 * with a task name and a date. Also, It provides methods for returning the stored date and a string of information of
 * the object in displaying/saving formats.
 */
public class Deadline extends Task {

    public static final String TASK_TYPE = "D";

    private final DateTime dueDay;

    /**
     * Constructor of {@code Deadline}<br>
     * Initializes the {@code Deadline} object with the given parameters.
     *
     * @param taskName name of the Deadline Object
     * @param dueDay   deadline of the Deadline Object
     */
    public Deadline(String taskName, String dueDay) {
        super(taskName);
        this.dueDay = new DateTime(dueDay);
    }

    /**
     * Returns the stored date of a {@code Deadline} object.
     *
     * @return date of the {@code Deadline} object
     */
    @Override
    public LocalDate getDate() {
        return dueDay.getDate();
    }

    /**
     * Returns a string of all information about the {@code Deadline} object for displaying in Command-Line Interface.
     *
     * @return A string of information about the {@code Deadline} object for CLI display
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by: " + dueDay.toString() + ")";
    }

    /**
     * Returns a string of all information about the {@code Deadline} object for storage in text file.
     *
     * @return A string of information about the {@code Deadline} object for saving
     */
    @Override
    public String toSave() {
        return TASK_TYPE + super.toSave() + " /by " + dueDay.toSave();
    }
}
