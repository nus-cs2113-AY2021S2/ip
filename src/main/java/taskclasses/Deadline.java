package taskclasses;

public class Deadline extends Task {
    protected String date;
    protected String time;
    public Deadline(String taskName, String date, String time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return ("[" + getTaskType() + "]" + super.toString() + " (by: " + getDate() + " " + getTime() + ")");
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public String getTime() {
        return this.time;
    }
    public String getDate() {
        return this.date;
    }
}
