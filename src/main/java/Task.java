public class Task {
    private String description;
    private boolean isDone;
    public String natureOfTask;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getNatureOfTask(){
        return this.natureOfTask;
    }

    public Boolean getIsDone(){
        return this.isDone;
    }

    public String getSpecialDescription(){
        return "";
    }

    public String toString(){
        return String.format("[" + this.getStatusIcon() + "] " + this.getDescription());
    }
}
