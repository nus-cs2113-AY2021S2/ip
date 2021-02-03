public class Deadline extends Event{

    Deadline(String content) {
        super(content);
    }

    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + name + "(by: " + time + ")";
    }
}
