package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    @Override
    public void printTask() {
        System.out.println("[T][" + getStatusIcon() + "] " + description);
    }
    @Override
    public String saveTask() {
        String done = String.valueOf(isDone);
        return done + " todo " + description + "\n";
    }
}
