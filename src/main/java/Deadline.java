public class Deadline extends Task{
    private static final String DEADLINE_TYPE = "D";
    protected String by;

    public Deadline(String errand, String timestamp) {
        super(errand);
        by = timestamp;
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
        String trialingBy = " (by: " + by + ")";
        System.out.print(trialingBy);
    }
}
