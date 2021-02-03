public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void getDone(boolean x){
        isDone = x;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}