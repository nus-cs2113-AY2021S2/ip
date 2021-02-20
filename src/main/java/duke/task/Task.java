package duke.task;

import java.io.Serializable;

import duke.DateTime;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;

public abstract class Task implements Serializable {
    public static final long serialVersionUID = 1L;

    // We set the default type icon to be *, which should be overwritten for child classes
    protected String typeIcon = "*";

    //@@author fsgmhoward-reused
    // Reused from https://nus-cs2113-ay2021s2.github.io/website/schedule/week3/project.html
    protected String description;
    protected boolean isDone;

    public Task(String description) throws InvalidInputException {
        if (description == null || description.isEmpty()) {
            throw new InvalidInputException(InputExceptionType.EMPTY_DESCRIPTION);
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // Return tick when done, or 'X' symbol when it is not done
        return (isDone ? "\u2713" : "\u2718");
    }
    //@@author

    public String getTypeIcon() {
        return typeIcon;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    // Check whether this task falls on a specific date
    public abstract Boolean isSameDate(DateTime dateTime);

    public String toString() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
}
