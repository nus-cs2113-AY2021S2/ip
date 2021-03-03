public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D] ["+ (this.isDone ? "X" : " ") + "] "
                + this.getDescription()  + " (by: " + this.by + ")";
    }

    @Override
    public String formatData() {
        int done;
        if (isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "D" + " | " + done + " | " + description + " | " + by;
    }

}