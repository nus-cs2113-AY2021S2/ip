package duke.task;

public class Event extends Task {
    private final String date;

    public Event(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }

    @Override
    public String getData() {
        return String.format("%c;%d;%s;%s", getTaskChar(), (isDone?1:0), taskName, date);
    }

    public static char getTaskChar() {
        return 'E';
    }
}
