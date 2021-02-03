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

    public void updateDoneStatus(){
        isDone = true;
    }

    public String getDateTime() {
        return null;
    }

    public String getTaskType() {
        return null;
    }

}
