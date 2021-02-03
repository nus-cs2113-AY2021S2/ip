public class Deadlines extends Task{

    private static  String by;

    public Deadlines(String description, String by){
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
