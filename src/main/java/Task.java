/**
 * Task object used in the Duke application,
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param description string description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets description of Task object.
     *
     * @return description of Task object
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Gets done status icon of Task.
     *
     * @return [X] if task is done, [ ] if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }

    /**
     * Sets done status of Task.
     */
    public void setDone(){
        this.isDone = true;
    }

    /**
     * Formats Task object into standard used by Duke application.
     *
     * @return formatted string of Task data
     */
    public String toString(){
        return getStatusIcon() + " " + getDescription();
    }

    /**
     * Formats data of Task object into standard used by the Storage class.
     *
     * @return formatted string of Task data
     */
    public String saveFormatString(){
        String isDoneBinary = null;
        if(this.isDone){
            isDoneBinary = "1";
        } else {
            isDoneBinary = "0";
        }
        return isDoneBinary + " | " + this.getDescription();
    }
}