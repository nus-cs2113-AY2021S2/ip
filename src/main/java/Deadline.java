public class Deadline extends Task{

    private static String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
        this.natureOfTask = "D";
    }

    public String getDeadline(){
        return this.by;
    }

    public String getSpecialDescription(){
        return this.getDeadline();
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
