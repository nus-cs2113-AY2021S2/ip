public class Event extends Tasks{
    private String by;
    public Event(String description, String taskType,String by){
        super(description, taskType);
        this.by = by;
        super.extraDescription = "( by: " + by + " )";

    }
}