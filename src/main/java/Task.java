public class Task {
    private boolean done;
    private String label;

    public Task(String label) {
        this.done = false;
        this.label = label;
    }

    public boolean isDone() {
        return done;
    }

    public String getLabel() {
        return label;
    }

    public void isDone(boolean done) {
        this.done = done;
    }

    public String getCheckbox() {
        return this.isDone() ? "[X]":"[ ]";
    }
}
