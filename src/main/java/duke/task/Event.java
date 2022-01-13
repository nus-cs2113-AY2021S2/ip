package duke.task;

public class Event extends Task {

    private static String at;

    public Event(String description, String at, Boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    public static String getAt() {
        return at;
    }
}
