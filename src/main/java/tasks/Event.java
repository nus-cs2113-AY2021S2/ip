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
            return "E " + "Y " + description + "/d" + at + "\n";
        }
        else {
            return "E " + "N " + description + "/d" + at + "\n";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatus() + " " + super.getDescription() + " (at: " + at + ")";
    }
}
