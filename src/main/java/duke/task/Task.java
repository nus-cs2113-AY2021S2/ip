package duke.task;

import java.io.Serializable;

import duke.DateTime;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;

/**
 * Base class of all tasks, providing necessary interfaces and methods for implementation
 */
public abstract class Task implements Serializable {
    public static final long serialVersionUID = 1L;

    public static final String CROSS_ICON = "\u2718";
    public static final String TICK_ICON = "\u2713";

    // We set the default type icon to be *, which should be overwritten for child classes
    protected String typeIcon = "*";

    //@@author fsgmhoward-reused
    // Reused from https://nus-cs2113-ay2021s2.github.io/website/schedule/week3/project.html
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of a task instance
     * @param description Description of the task, cannot be empty
     * @throws InvalidInputException This is thrown if the task description is null or an empty string
     */
    public Task(String description) throws InvalidInputException {
        if (description == null || description.isEmpty()) {
            throw new InvalidInputException(InputExceptionType.EMPTY_DESCRIPTION);
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get a status icon for the task
     * @return a UTF-8 tick icon for a completed task, and a cross for an incomplete task
     */
    public String getStatusIcon() {
        // Return tick when done, or 'X' symbol when it is not done
        return (isDone ? TICK_ICON : CROSS_ICON);
    }
    //@@author

    /**
     * Get the type icon of the task type (e.g. D for deadline)
     * @return the type icon of the current task (subclass)
     */
    public String getTypeIcon() {
        return typeIcon;
    }

    /**
     * Get the description of the task
     * @return Description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mark the task as completed
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Check whether this task falls on a specific date
     * @param dateTime The date for comparison
     * @return True when this task falls on the given `dateTime`
     */
    public abstract Boolean isSameDate(DateTime dateTime);

    /**
     * Converts this task to a descriptive string
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
}
