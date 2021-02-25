package duke.task;

/**
 * Represents a task type called deadline
 */
public class Deadline extends Task {
    protected String timeConstraint;

    public Deadline(String taskName, String taskType, String timeConstraint) {
        super(taskName, taskType);
        this.timeConstraint = timeConstraint;
    }

    /**
     * Getter method for time constraint
     * @return time constraint for this deadline
     */
    public String getTimeConstraint() { return timeConstraint; }

    /**
     * Setter method for time constraint
     * @param timeConstraint The time constraint to be set
     */
    public void setTimeConstraint(String timeConstraint) { this.timeConstraint = timeConstraint; }

    /**
     * Prints task in a readable format
     */
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

    /**
     * Formats the task for storage
     * @return The task formatted for storage
     */
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
