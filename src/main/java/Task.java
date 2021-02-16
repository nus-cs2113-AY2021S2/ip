public class Task {
    private String desc;
    private boolean isDone;

    private static int tasksRemaining = 0;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
        tasksRemaining++;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
        if (!isDone) {
            tasksRemaining++;
        }
    }

    public String getDesc() {
        return desc;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDate() {
        return null;
    }

    public String getStatusSymbol() {
        return (isDone ? " \u2612 " : " \u2610 ");
    }

    public static int getTasksRemaining() {
        return tasksRemaining;
    }

    public void check() {
        if (isDone == false) {
            isDone = true;
            tasksRemaining--;
        }
    }

    public void uncheck() {
        if (isDone == true) {
            isDone = false;
            tasksRemaining++;
        }
    }

    public String toString() {
        return getStatusSymbol() + getDesc();
    }
}
