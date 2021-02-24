package duke.task;

public class Deadline extends Task {

    private static String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public static String getBy() {
        return by;
    }
}
