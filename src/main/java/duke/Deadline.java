package duke;

public class Deadline extends Task {
    private static final String TASK_SYMBOL_D = "D";
    private String taskTiming;

    public Deadline(String taskDescrption, String taskTiming) {
        super(taskDescrption);
        this.taskTiming = taskTiming;
    }

    @Override
    public String getTaskSymbol() {
        return TASK_SYMBOL_D;
    }

    @Override
    public void printTaskDetails() {
        super.printTaskDetails();
        this.printTaskTiming();
    }

    public void printTaskTiming() {
        System.out.print("(by: " + this.taskTiming + ")");
    }
}
