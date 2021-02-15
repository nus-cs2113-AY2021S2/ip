package duke.task;

public class Event extends Task {

    String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public static void printEventDescription() {
        System.out.println("Gotcha! I've added this task:");
        System.out.print("[E]");
    }
}
