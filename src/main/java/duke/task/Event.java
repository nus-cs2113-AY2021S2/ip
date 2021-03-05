package duke.task;

import duke.task.dateTime.DateTime;

import java.time.LocalDate;

/**
 * Represents a {@code Event} object, which is inherited from {@code Task}. An {@code Event} object is initialized
 * with a task name and a date. Also, It provides methods for returning the stored date and a string of the information
 * of the object in displaying/saving formats.
 */
public class Event extends Task {

    public static final String TASK_TYPE = "E";

    private final DateTime schedule;

    /**
     * Constructor of {@code Event}<br>
     * Initializes the {@code Event} object with the given parameters.
     *
     * @param taskName name of the {@code Event} Object
     * @param schedule date and time (optional) of the {@code Event} Object
     */
    public Event(String taskName, String schedule) {
        super(taskName);
        this.schedule = new DateTime(schedule);
    }

    /**
     * Returns the stored date of the {@code Event} object.
     *
     * @return date of the {@code Event} object
     */
    @Override
    public LocalDate getDate() {
        return schedule.getDate();
    }

    /**
     * Returns a string of all information about the {@code Event} object for displaying in Command-Line Interface.
     *
     * @return A string of information about the {@code Event} object for CLI display
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at: " + schedule.toString() + ")";
    }

    /**
     * Returns a string of all information about the {@code Event} object for storage in text file.
     *
     * @return A string of information about the {@code Event} object for saving
     */
    @Override
    public String toSave() {
        return TASK_TYPE + super.toSave() + " /at " + schedule.toSave();
    }
}
