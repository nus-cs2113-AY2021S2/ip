package duke.task;

import java.time.LocalDate;

/**
 * It is an abstract class that represents general user task. A {@code Task} object stores information of a user task
 * such as {@code taskName} and {@code isDone} and perform functions related marking "done" and task string production.
 */
abstract public class Task {
    private final String taskName;
    private boolean isDone = false;

    /**
     * Constructor of Task<br>
     * Initializes the Task object with the given parameter.
     *
     * @param taskName name of the Task object
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * It is an abstract method that returns date of a task. Behaviour of which are override in each subclass.
     *
     * @return date of a task
     */
    abstract public LocalDate getDate();

    /**
     * Sets the Task object as Done.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns name of task stored in the Task Object
     *
     * @return name of task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * It is an abstract method that returns type of a task. Behaviour of which are override in each subclass.
     *
     * @return type of a task
     */
    abstract public String getTaskType();

    /**
     * Returns a string of part of information about the Task object for displaying in Command-Line Interface
     *
     * @return A string of part of information about the Task object for displaying in CLI
     */
    @Override
    public String toString() {
        String mark = isDone ? "[X] " : "[ ] ";
        return mark + taskName;
    }

    /**
     * Returns a string of part of information about the Task object for storage in text file
     *
     * @return A string of part of information about the Task object for saving
     */
    public String toSave() {
        String mark = isDone ? "|1|" : "|0|";
        return mark + taskName;
    }
}
