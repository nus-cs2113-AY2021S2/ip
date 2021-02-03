public class event extends Task {

    protected String by;
    protected boolean isDeadline;

    public event(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getType(){
        return "E";
    }
}