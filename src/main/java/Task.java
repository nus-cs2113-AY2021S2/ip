public class Task {
    private String desc;
    private boolean isDone;

    private static int tasksRemaining = 0;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
        tasksRemaining++;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusSymbol() {
        return (isDone ? " \u2612 " : " \u2610 ");
    }

    public static int getTasksRemaining() {
        return tasksRemaining;
    }

    public void check() {
        isDone = true;
        tasksRemaining--;
    }

    public void uncheck() {
        isDone = false;
        tasksRemaining++;
    }
}
