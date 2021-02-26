package duke.task;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.deadline = by;
        this.taskType = "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String taskToText() {
        return "D|" + super.completed + "| " + super.taskDescription + "|" + deadline;
    }
}
