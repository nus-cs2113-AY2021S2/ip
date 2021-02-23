package task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    /**
     * @return partial display message when event added
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * format to save event into save file
     * @return String to save event into save file
     */
    public String saveToFile() {
        String done = "1";
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return done + " event " + description + " /at " + at + "\n";
    }
}
