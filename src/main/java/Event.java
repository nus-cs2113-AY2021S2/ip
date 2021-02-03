public class Event extends Task{
    String description;
    public Event(String description) {
        super(description);
        String[] stringArray = description.split("/at");
        this.description = stringArray[0] + "(at:" + stringArray[1] + ")";
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
