package duke;

/**
 * Represents a task which serves as a base class for different specific types of task classes,
 * such as Event, Deadline and ToDo.
 * The class contains the task descriptions, state, and some basic manipulations of the information
 * in the task such as printing the task information.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task class.
     *
     * @param description Textual description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task in String format.
     *
     * @return the description of the task in String format.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a boolean representation denoting if the task is already completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean getIsDone(){
        return isDone;
    }

    /**
     * Modify the state of completion of the task.
     *
     * @param isDone true if the task is completed, false otherwise.
     */
    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    /**
     * Returns an integer representation denoting if the task is already completed.
     *
     * @return 1 if the task is completed, 0 otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Prints the information of the task
     */
    public void printTask(){
        System.out.printf("[%s] %s\n",getStatusIcon(), getDescription());
    }
}
