package duke.task;

public class Deadline extends Task {
    String endTime;
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        return getStatusIcon() + super.getDescription() + "(by: " + endTime + ")";
    }
}
