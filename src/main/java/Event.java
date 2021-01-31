public class Event extends Task {
    private static final String TASK_SYMBOL_EVENT = "E";
    private String taskTiming;

    public Event(String taskDescrption, String taskTiming) {
        super(taskDescrption);
        this.taskTiming = taskTiming;
    }

    @Override
    public String getTaskSymbol() {
        return TASK_SYMBOL_EVENT;
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
