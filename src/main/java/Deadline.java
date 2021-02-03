public class Deadline extends Event{

    Deadline(String name, String time) {
        super(name, time);
    }

    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + name + "(by: " + time + ")";
    }
}
