package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveToFile() {
        String done = "1";
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return done + " todo " + description + "\n";
    }
}
