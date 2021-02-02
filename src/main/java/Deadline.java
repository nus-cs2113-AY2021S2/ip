public class Deadline extends Task {

    private final String dateBy;


    public Deadline(String task, String dateBy) {
        super(task);
        this.dateBy = dateBy;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][\u2713]" : "[D][\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        String deadlineDescription = String.format("%s(by: %s)", description, dateBy);
        return deadlineDescription;
    }

}
