package Duke.Task;

public class EventTask extends Task {

    private static final String COMMAND_EVENT_WORD = "event";

    public EventTask(String taskDetail) {
        super(taskDetail);
    }

    @Override
    public String getTaskType() {
        return COMMAND_EVENT_WORD;
    }

    @Override
    public String toString() {
        return "["+COMMAND_EVENT_WORD+"]" + super.toString();
    }

    @Override
    public void markAsDone() {
        super.isDone = true;
    }
}
