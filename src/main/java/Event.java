public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[D] ["+ (this.isDone ? "X" : " ") + "] "
                + this.getDescription() + "(at: " + getAt() + ")";
    }

    @Override
    public String formatData() {
        int done;
        if (isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "E" + " | " + done + " | " + description + " | " + at;
    }

}