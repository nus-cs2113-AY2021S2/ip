public class Deadline extends Task {

    protected String by;
    protected boolean isDeadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getType(){
        return "D";
    }
}