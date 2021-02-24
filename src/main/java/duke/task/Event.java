package duke.task;

public class Event extends Task {

    private static String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static String getAt() {
        return at;
    }
}
