package duke;

public class Task {

    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return "";
    }

    public String getStatusIcon(boolean isDone) {
    return (isDone ? "[X] " : "[ ] ");
}


    public void setDone() {
        isDone = true;
    }

//    public boolean isDone() {
//        return isDone;
//    }

    @Override
    public String toString() {
        return getStatusIcon(isDone) + description;
    }

}

