public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" + " " + description : "[ ]" + " " + description); //return tick or X symbols
    }

    public void markAsDone(){
        isDone = true;
    }
}
