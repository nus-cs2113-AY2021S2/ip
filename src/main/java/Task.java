public class Task {
    protected boolean done;
    protected String label;

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

    @Override
    public String toString() {
        return getLabel();
    }

    public void isDone(boolean done) {
        this.done = done;
    }

    // Returns a checkbox string depending on whether task is done
    public String getCheckbox() {
        return this.isDone() ? "[✔]":"[❌]";
    }

    // Returns a string depending on type of Task
    public String getTypeString() {
        return "[ ]";
    }
}
