public class EventTask extends Task {

    private String eventInfo;

    public EventTask(String name, String dateTime) {
        super(name);
        setEventInfo(dateTime);
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    @Override
    public void printStatus() {
        System.out.print("[E]");
        super.printStatus();
        System.out.print(" (at: " + eventInfo + ")");
    }
}
