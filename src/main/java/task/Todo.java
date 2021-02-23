package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    /**
     * @return partial display message when todo added
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * format to save todo into save file
     * @return String to save todo into save file
     */
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
