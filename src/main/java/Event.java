public class Event extends Task{
    private static final String EVENT_TYPE = "E";
    private String at;
    private String timestampHeader;

    public Event (String errand, String timestamp, String timestampHeader) {
        super(errand);
        this.at = timestamp;
        this.timestampHeader = timestampHeader;
    }

    @Override
    protected String getTaskType() {
        return EVENT_TYPE;
    }

    @Override
    protected void printTaskItem() {
        super.printTaskItem();
        this.printAt();
    }

    private void printAt() {
        String trialingAt = " (" + timestampHeader + ": " + at + ")";
        System.out.print(trialingAt);
    }
}
