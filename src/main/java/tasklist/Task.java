package tasklist;

public class Task {

    public int isCorrupted;
    public boolean isDone;
    public String description;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isCorrupted = 0;
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
