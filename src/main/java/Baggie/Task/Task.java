package Baggie.Task;

import Baggie.Exceptions.EmptyDateException;
import Baggie.Exceptions.IllegalDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Baggie.UI.TEXT.*;

public class Task {
    protected String taskDescription;
    protected boolean isDone;

    /**
     * Initializes a task with task description.
     *
     * @param taskDescription
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Gets the status of a task.
     *
     * @return The done icon of a task.
     */
    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    /**
     * Sets the status of a task to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the status of a task.
     *
     * @return a boolean value of the status of a task.
     */
    public boolean isDone() { return isDone; }

    /**
     * Gets the task description of a task.
     *
     * @return the task description of a task in String.
     */
    public String getTask() {
        return taskDescription;
    }

    /**
     * Gets the task type of a task.
     *
     * @return the task type of a task in String.
     */
    public String getTaskType() {
        return null;
    }

    /**
     * Gets the task time of a task.
     *
     * @return the task time of a task in String.
     */
    public String getTaskTime() {
        return null;
    }

    /**
     * Returns a task summary.
     *
     * @return a task summary in String.
     */
    public String toString() {
        return LEFT_SQUARE_BRACKET + getStatusIcon() + RIGHT_SQUARE_BRACKET + getTask();
    }
}
