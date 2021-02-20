package duke.task;

public class Deadline extends Task{
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        super.setTaskType('D');
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }

    @Override
    public String exportAsCSV() {
        return super.exportAsCSV() + "," + dueDate;
    }
}
