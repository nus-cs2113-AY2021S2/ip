package duke.task;

public class Deadline extends Task {
    protected String timeConstraint;

    public Deadline(String taskName, String taskType, String timeConstraint) {
        super(taskName, taskType);
        this.timeConstraint = timeConstraint;
    }

    public String getTimeConstraint() { return timeConstraint; }

    public void setTimeConstraint(String timeConstraint) { this.timeConstraint = timeConstraint; }

    @Override
    public void printTask() {
        String output;
        if (isCompleted) {
            output = "[D][X] " + taskName;
        } else {
            output = "[D][ ] " + taskName;
        }
        output += " (by: " + timeConstraint + ")";
        System.out.println(output);
    }

    @Override
    public String formatTaskToWrite() {
        String formattedTask;
        formattedTask = String.join(
                "<separator>",
                taskType,
                taskName,
                timeConstraint,
                String.valueOf(isCompleted)
        );
        return formattedTask;
    }
}
