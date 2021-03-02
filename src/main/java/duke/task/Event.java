package duke.task;

public class Event extends Task {
    protected String eventDateAndTime;

    public Event(String taskDescription, String formattedDateAndTime) {
        super(taskDescription);
        this.eventDateAndTime = formattedDateAndTime;
        this.taskType = "[E]";
    }

    /**
     * Prints task to user.
     */
    @Override
    public String toString() {
        return super.toString() + "(at: " + eventDateAndTime + ")";
    }

    /**
     * Translates data into text format.
     */
    @Override
    public String taskToText() {
        return "E|" + super.completed + "| " + super.taskDescription + "|" + eventDateAndTime;
    }
}
