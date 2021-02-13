package tasks;

public class EventTask extends Task {

    private String eventInfo;

    public EventTask(String name, String dateTime) {
        super(name);
        setEventInfo(dateTime);
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    /**
     * Prints task status.
     */
    @Override
    public void printStatus() {
        System.out.print("[E]");
        super.printStatus();
        System.out.print(" (at: " + eventInfo + ")");
    }

    /**
     * Outputs formatted data for saving.
     */
    @Override
    public String formatData() {
        return "event " + getName() + " /at " + eventInfo + "\n"
                + getDone() + "\n";
    }
}
