public abstract class Task {
    protected boolean isDone;
    protected String label;

    public Task(String label) {
        this.isDone = false;
        this.label = label;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getLabel() {
        return label;
    }

    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        return getLabel();
    }

    public void isDone(boolean done) {
        this.isDone = done;
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
