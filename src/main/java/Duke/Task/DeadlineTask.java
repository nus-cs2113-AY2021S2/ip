package Duke.Task;

public class DeadlineTask extends Task {

    protected String time;

    public DeadlineTask(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    @Override
    public String getTaskTime() {
        return time;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
