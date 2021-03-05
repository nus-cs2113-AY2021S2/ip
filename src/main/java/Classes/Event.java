package Classes;

public class Event extends Task {
    protected String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at= at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }

    public String formatString() {
        int done = (isDone ? 1 : 0);
        return "E-" + done + "-" + description + "-" + at + "\n";
    }
}

