public class Deadline extends Task {

    private final String dateBy;
    private int numberOfDaysLeft;


    public Deadline(String task, String dateBy) {
        super(task);
        this.dateBy = dateBy;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][\u2713]" : "[D][\u2718]"); //return tick or X symbols
    }

    @Override
    public String getDescription() {
        String deadlineDescription = String.format("%s (by: %s)", description, dateBy);
        return deadlineDescription;
    }



}
