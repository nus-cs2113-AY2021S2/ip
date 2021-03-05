package Classes;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return String containing description of Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return String of formatted data to be written into the text file
     */
    public String formatString() {
        int done = (isDone ? 1 : 0);
        return "T-" + done + "-" + description + "\n";
    }
}
