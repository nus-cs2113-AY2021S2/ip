package duke.task;


/**
 * Instances of the Task class represent a 'todo' in a list.
 * Each Task object has a description, a flag to indicate if it is done
 * and a character to denote the type of task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private char taskType;

    /**
     * Creates a new Task object with the specified description.
     *
     * @param description a description for the task to be added
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = ' ';
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns the description of a task.
     *
     * @return String containing the description of a task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the specified task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return the string representation of a task
     */
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the fields of a task (is done flag, task type and description)
     * Fields are separated by commas for exporting to a CSV file
     *
     * @return CSV-formatted representation of a Task
     */
    public String exportAsCSV() {
        return isDone + "," + taskType + "," + description;
    }
}
