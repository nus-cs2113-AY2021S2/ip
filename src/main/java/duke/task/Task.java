package duke.task;

public class Task {
    protected String name;
    protected boolean isDone;
    public static int totalNumberOfTasks = 0;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean checkDone() {
        return this.isDone;
    }
    /**
     * @return "x" if duke.task is done, empty space otherwise
     */
    public String getStatusIcon() {
        return (isDone? "\u2718" : " ");
    }

    public String getName() {
        return name;
    }
    // To be overridden
    public String getType() {
        return "[ ]";
    }
    // To be overridden
    public String getDate() {
        return "";
    }
    // To be overridden
    public String getTime() {
        return "";
    }
}
