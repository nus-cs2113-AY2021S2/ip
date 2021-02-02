public class Event extends Task {
    private final String eventDateBy;


    public Event(String task, String eventDateBy) {
        super(task);
        this.eventDateBy = eventDateBy;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[E][\u2713]" : "[E][\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        String deadlineDescription = String.format("%s(at:%s)", description, eventDateBy);
        return deadlineDescription;
    }

}
