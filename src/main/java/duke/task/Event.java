package duke.task;

public class Event extends Task {
    private String eventDate;

    public Event(String description, String eventDate) {
        super(description);
        super.setTaskType('E');
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (on: " + eventDate + ")";
    }

    @Override
    public String exportAsCSV() {
        return super.exportAsCSV() + "," + eventDate;
    }
}
