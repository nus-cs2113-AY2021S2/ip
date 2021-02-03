public class Deadline extends Task{
    String description;
    public Deadline(String description) {
        super(description);
        String[] stringArray = description.split("/by");
        this.description = stringArray[0] + "(by:" + stringArray[1] + ")";
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
