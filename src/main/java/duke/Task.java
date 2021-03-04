package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String separator = "";

    /**
     * Constructor for Task class
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string form of the specific type of task
     *
     * @return string description of the specific type of task
     */
    public String getType() {
        return "task";
    }

    /**
     * Returns an icon to represent the completion status of a task
     *
     * @return string icon representing task completion status
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns an icon to represent the completion status of a task
     *
     * @return
     */
    public String getStatusIcon() {
        //return space or X symbols
        return (isDone ? "X" : " ");
    }


    /**
     * Returns the completion status of this task
     *
     * @return completion status of this task
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Returns the separator of Task class, for writing into the Storage file
     *
     * @return the separator of Task class
     */
    public String getSeparator() {
        return this.separator;
    }

    /**
     * Sets a particular instance of a Task as completed
     */
    public void setAsDone(){
        this.isDone = true;
    }

    /**
     * Returns the task in string form
     *
     * @return String form of the task
     */
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
