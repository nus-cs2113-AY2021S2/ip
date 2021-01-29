public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        setDeadlineTime(deadlineTime);
    }

    public void setDeadlineTime(String deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDeadlineTime() + ")";
    }
}
