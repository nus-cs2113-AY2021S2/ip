package Duke.Task;

public class EventTask extends Task {

    protected String time;

    public EventTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getTaskTime(){
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
