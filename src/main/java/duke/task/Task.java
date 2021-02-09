package duke.task;

public class Task {
    private String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getContent() {
        return content;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + content;
    }
}
