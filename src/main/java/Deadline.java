public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public static void printDeadlineDescription() {
        System.out.println("Gotcha! I've added this task:");
        System.out.print("[D]");
    }
}
