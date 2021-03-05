package duke.task;

import java.time.LocalDate;

/**
 * It is an abstract super class that represents the core of three subclass ({@code Todo/Deadline/Event}).
 * Class {@code Task} handles task name initialization and setting task to be done. Also, it provides methods to
 * return a string of task information for displaying via CLI or saving in local text file.
 */
abstract public class Task {
    private final String taskName;
    private boolean isDone = false;

    /**
     * Constructor of {@code Task}<br>
     * Initializes the {@code Task} object with the given parameter.
     *
     * @param taskName name of the {@code Task} object
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    abstract public LocalDate getDate();

    public void setAsDone() {
        isDone = true;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns a string of part of information about the {@code Task} object for displaying in Command-Line Interface
     *
     * @return A string of part of information about the {@code Task} object for displaying in CLI
     */
    @Override
    public String toString() {
        String mark = isDone ? "[✓] " : "[X] ";
        return mark + taskName;
    }

    /**
     * Returns a string of part of information about the {@code Task} object for storage in text file
     *
     * @return A string of part of information about the {@code Task} object for saving
     */
    public String toSave() {
        String mark = isDone ? "|1|" : "|0|";
        return mark + taskName;
    }
}
