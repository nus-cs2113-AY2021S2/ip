public class Event extends Todo{
    protected String time;

    Event(String content) {
        String name = content.substring(0, content.indexOf('/') - 1);
        String time = content.substring(content.indexOf('/') + 4);
        this.name = name;
        this.time = time;
    }

    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + name + "(at: " + time + ")";
    }
}
