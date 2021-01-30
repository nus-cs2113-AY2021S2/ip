public class Event extends Task {

    protected String at;

    public Event(String description, String by) {
        super(description);
        this.at = by;
    }

    @Override
    public String getType(){
        return "E";
    }

    @Override
    public String getDate() {
        return " (at: " + at + ")";
    }
}