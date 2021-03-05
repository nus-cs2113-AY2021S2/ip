package duke.tasks;

public class Deadline extends Task {

    protected String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public boolean hasDateTime() {
        return true;
    }
}

