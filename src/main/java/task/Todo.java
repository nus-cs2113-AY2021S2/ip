package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    @Override
    public void printTask() {
        System.out.println("[T][" + getStatusIcon() + "] " + description);
    }
    public String saveTask() {
        return isDone + " todo " + description;
    }
}
