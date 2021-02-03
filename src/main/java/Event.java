public class Event extends Todo{
    protected String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + name + "(at: " + time + ")";
    }
}
