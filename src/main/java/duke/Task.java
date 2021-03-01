package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String separator = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType() {
        return "task";
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        //return space or X symbols
        return (isDone ? "X" : " ");
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String getSeparator() {
        return this.separator;
    }

    public void setAsDone(){
        this.isDone = true;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
