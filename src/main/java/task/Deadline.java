package task;

public class Deadline extends Task {

    protected String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }
    @Override
    public void printTask() {
        System.out.println("[D][" + getStatusIcon() + "] " + description + "(by:" + deadlineDate +")");
    }
    public String saveTask() {
        String done = String.valueOf(isDone);
        return done + " deadline " + description + " /at " + deadlineDate + "\n";
    }
}
