package duke.task;

public class Deadline extends Task {
    private static final String DEADLINE_TYPE = "D";
    private String by;
    private String timestampHeader;

    public Deadline(String errand, String timestamp, String timestampHeader) {
        super(errand);
        this.by = timestamp;
        this.timestampHeader = timestampHeader;
    }

    @Override
    protected String getTaskType() {
        return DEADLINE_TYPE;
    }

    @Override
    protected void printTaskItem() {
        super.printTaskItem();
        this.printBy();
    }

    private void printBy() {
        String trialingBy = " (" + timestampHeader + ": " + by + ")";
        System.out.print(trialingBy);
    }
}
