public class Deadline extends Task{
    protected static String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String getDate() {
        return "(by: " + this.deadline + ")";
    }
}
