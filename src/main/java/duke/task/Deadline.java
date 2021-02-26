package duke.task;

public class Deadline extends Task {
    private static final String DEADLINE_TYPE = "D";
    private String timestamp;
    private String timestampHeader;

    public Deadline(String errand, String timestamp, String timestampHeader) {
        super(errand);
        this.timestamp = timestamp;
        this.timestampHeader = timestampHeader;
    }

    @Override
    public String getTaskType() {
        return DEADLINE_TYPE;
    }

    @Override
    public String getTaskItem() {
        return this.description + " (" + this.timestampHeader + ": " + this.timestamp + ")";
    }
}
