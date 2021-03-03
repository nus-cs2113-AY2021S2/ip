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
    public String getStatusNum() {
        return (isDone ? "1" : "0");
    }


    public String toString() {
        return "[" + getStatusIcon() + "]" + content;
    }

    public String strAddToTxt() { return " | " + getStatusNum() + " | " + content; }
}
