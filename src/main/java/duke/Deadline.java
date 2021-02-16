package duke;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String item, String deadline) {
        super(item);
        this.deadline = deadline;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.print(" (by: " + this.getDeadline() + ")");
    }

    @Override
    public String toString() {
        return super.toString() + " | " + this.getDeadline();
    }

    public String getDeadline() {
        return deadline;
    }

}
