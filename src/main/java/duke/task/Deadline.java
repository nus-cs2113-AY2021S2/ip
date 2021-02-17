package duke.task;

public class Deadline extends Task {
    private final String date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date);
    }

    @Override
    public String getData() {
        return String.format("%c;%d;%s;%s", getTaskChar(), (isDone ? 1 : 0), taskName, date);
    }

    public static char getTaskChar() {
        return 'D';
    }
}
