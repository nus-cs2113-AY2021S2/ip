public class Deadline extends Task {

    protected String by;

    public Deadline (String description, String by) {
        super(description+ "(by:" + by + ")");
        this.by = by;
        this.taskType = "[D]";
    }

}
