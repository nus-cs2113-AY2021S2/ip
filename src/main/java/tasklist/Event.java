package tasklist;

import tasklist.Task;

public class Event extends Task {

    public String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }


    public String getStatusIcon() {
        return "[E]" + "[" + super.getStatusIcon() + "]";
    }


    @Override
    public String getDescription() {
        return super.getDescription() + " " + "<< " + by + " >>";
    }
}
