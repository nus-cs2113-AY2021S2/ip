package duke;

public class Event extends Task{
    protected String at;

    public Event(String description, char taskType, String at){
        super(description);
        this.taskType = taskType;
        this.at = at;
    }

    public String getAt() {return at;}

    /**
     * Returns the complete Event task description in task list
     * @return the task type, status and description in the task list
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
