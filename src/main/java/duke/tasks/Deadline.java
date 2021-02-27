package duke.tasks;

public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getDate() {
        return date;
	}

    public String printDate() {
        return " (by: " + date + ")";
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    public String getDetails() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription() + printDate();
    }

}
