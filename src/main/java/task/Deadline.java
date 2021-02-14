package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType(){
        return "D";
    }

    @Override
    public String getDate() {
        return " (by: " + by + ")";
    }

    @Override
    public String outputData() {
        return "[" + getStatusIcon() + "] " + "deadline " + getName() + " /by " + by;
    }
}