public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String toString() {
        return getStatusIcon() + description;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]" ); //return tick or X symbols
    }

    public void markAsDone(){
        isDone = true;
    }

    public String stringToSave() {
        return "";
    }

}
