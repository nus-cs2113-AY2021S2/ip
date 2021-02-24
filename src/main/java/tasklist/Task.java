package tasklist;

public class Task {

    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription(){
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public String printStatus(){
        return("[ " + getStatusIcon() + "] " + description);
    }


}
