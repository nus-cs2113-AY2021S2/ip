public class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String representAsString() {
        return "[" + this.getStatusIcon() + "]" + name;
    }
}
