package tasks;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toSaveFormat() {
        if (isDone) {
            return "E " + "Y " + description + "/d" + at;
        }
        else {
            return "E " + "N " + description + "/d" + at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatus() + " " + super.getDescription() + " (at: " + at + ")";
    }
}
