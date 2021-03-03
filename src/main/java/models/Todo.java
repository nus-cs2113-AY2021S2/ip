package models;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return String describing the Todo Task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return String with formatted Data to be written onto Text file
     */
    @Override
    public String formatData() {
        int done;
        if (isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "T" + "#" + done + "#" + description + "\n";
    }
}