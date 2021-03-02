package duke.task;

public class Deadline extends Task {
    protected String deadlineDateAndTime;

    public Deadline(String taskDescription, String formattedDateAndTime) {
        super(taskDescription);
        this.deadlineDateAndTime = formattedDateAndTime;
        this.taskType = "[D]";
    }

    /**
     * Prints task to user.
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + deadlineDateAndTime + ")";
    }

    /**
     * Translates data into text format.
     */
    @Override
    public String taskToText() {
        return "D|" + super.completed + "| " + super.taskDescription + "|" + deadlineDateAndTime;
    }
}
