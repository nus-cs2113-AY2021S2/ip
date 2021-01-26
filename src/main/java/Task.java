public class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
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

    public void check() {
        isDone = true;
    }
}
