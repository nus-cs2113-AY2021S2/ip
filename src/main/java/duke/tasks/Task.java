package duke.tasks;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]":"[ ]");
    }

    public boolean getIsDone() {
        if (isDone) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setDone(){
        isDone = true;
    }

    public String getDateTime() {
        return "";
    }

    public String getTaskType() {
        return null;
    }

    public boolean hasDateTime() {
        return false;
    }

}
