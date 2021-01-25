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

    /**
     * @return "x" if task is done, empty space otherwise
     */
    public String getStatusIcon() {
        return (isDone? "\u2718" : " ");
    }

    public String getName() {
        return name;
    }
}
