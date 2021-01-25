public class Task {
    protected String name;
    protected boolean isDone;
    public static int totalTasks = 0;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone? "\u2718" : " ");
    }

    public String getName() {
        return name;
    }
}
