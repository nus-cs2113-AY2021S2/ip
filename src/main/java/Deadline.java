public class Deadline extends TaskList{
    protected String by;

    public Deadline(String description, String by) {
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

