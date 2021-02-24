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

    /*
    @Override
    public void printTaskItem() {
        super.printTaskItem();
        this.printBy();
    }
     */

    @Override
    public String getTaskItem() {
        return this.description + "(" + this.timestampHeader + ": " + this.timestamp + ")";
    }

    public void printBy() {
        String trialingBy = " (" + timestampHeader + ": " + timestamp + ")";
        System.out.print(trialingBy);
    }
}
