public class Event extends Task{

    protected String by;
    public Event(String description, String by) {
        super(description);
        this.by = by;
    }


    public String getStatusIcon() {
        return "[E]" + "[" + super.getStatusIcon() +"]";
    }


    @Override
    public String getDescription() {
        return super.getDescription() + " " + "--" + by + "--";
    }
}
