package duke;

public class DeadLines extends Task{

    private String by;

    public DeadLines(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public void printStatus() {
        System.out.print("[D]");
        super.printStatus();
        System.out.println("(by: "+by+")");
    }
}
