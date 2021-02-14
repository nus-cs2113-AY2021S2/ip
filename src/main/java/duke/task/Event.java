package duke.task;

public class Event extends Task {
    private String date;

    public Event(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }

    @Override
    public char getTaskChar() {
        return 'E';
    }
}
