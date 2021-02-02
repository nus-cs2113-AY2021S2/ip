public class Event extends Task{
    private static final String EVENT_TYPE = "E";
    private String at;

    public Event (String errand, String timestamp) {
        super(errand);
        this.at = timestamp;
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
        String trialingAt = " (at: " + at + ")";
        System.out.print(trialingAt);
    }
}
