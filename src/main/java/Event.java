public class Event extends Task{
    private String at;

    public Event(String description, String atInput) {
        super(description);
        this.at = atInput;
    }
    public void setAt(String atInput) {
        this.at = atInput;
    }
    public String getAt() {
        return this.at;
    }
    @Override
    public String getType() {
        return "E";
    }
}