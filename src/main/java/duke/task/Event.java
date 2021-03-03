package duke.task;

import duke.task.dateTime.DateTime;

import java.time.LocalDate;

/**
 * Represents an event inherited from {@code Task}<br>
 * A {@code Event} object consists of two parameters:<br>
 * 1. {@code taskName}: name of the Event object<br>
 * 2. {@code schedule}: date and time [optional] of the target day
 */
public class Event extends Task {

    public static final String TASK_TYPE = "E";

    private final DateTime schedule;

    /**
     * Constructor of Event<br>
     * Initializes the Event object with the given parameters
     *
     * @param taskName name of the Event Object
     * @param schedule target day (date and time) of the Event Object
     */
    public Event(String taskName, String schedule) {
        super(taskName);
        this.schedule = new DateTime(schedule);
    }

    /**
     * Returns the date of target day of the Event object
     *
     * @return date of the Event object
     */
    @Override
    public LocalDate getDate() {
        return schedule.getDate();
    }

    /**
     * Returns the task type of the Event object, i.e. "E"
     *
     * @return Task type of the Event object
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Returns a string of all information about the Event object for displaying in Command-Line Interface
     *
     * @return A string of information about the Event object for CLI display
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at: " + schedule.toString() + ")";
    }

    /**
     * Returns a string of all information about the Event object for storage in text file
     *
     * @return A string of information about the Event object for saving
     */
    @Override
    public String toSave() {
        return TASK_TYPE + super.toSave() + " /at " + schedule.toSave();
    }
}
