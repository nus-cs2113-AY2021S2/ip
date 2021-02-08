package duke.task;

public class Deadline extends Task {
    private String date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date);
    }
}
