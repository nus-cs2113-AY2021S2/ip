public class Event extends Task {
    protected String timeConstraint;

    public Event(String taskName, String taskType, String timeConstraint) {
        super(taskName, taskType);
        this.timeConstraint = timeConstraint;
    }

    public String getTimeConstraint() { return timeConstraint; }

    public void setTimeConstraint(String timeConstraint) { this.timeConstraint = timeConstraint; }

    @Override
    public void printTask() {
        String output;
        if (isCompleted) {
            output = "[E][X] " + taskName;
        } else {
            output = "[E][ ] " + taskName;
        }
        output += " (at: " + timeConstraint + ")";
        System.out.println(output);
    }
}
