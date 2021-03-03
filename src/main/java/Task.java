public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String formatData() {
        int done;
        if (isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return  " | " + done + " | " + description;
    }

}