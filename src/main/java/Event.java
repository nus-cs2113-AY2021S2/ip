public class Event implements Task {
    private final String description;
    private final boolean isDone;
    private final int index;
    private final String message;
    private final String event;

    /**
     * Constructs an event task.
     * Requires the task number, a task description, indication if task is done and the event.
     */
    public Event(int index, String description, boolean isDone, String event) {
        String fullDescription = String.format("%s (at: %s)", description, event);
        this.description = fullDescription;
        this.event = event;
        this.isDone = isDone;
        this.index = index;
        this.message = String.format("[E][%s] %s",
                isDone ? "X" : " ",
                fullDescription
        );
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public String getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return String.format("%d.%s", getIndex(), getMessage());
    }
}
