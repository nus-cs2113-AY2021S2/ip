public class Task {
    private final String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return "";
    }

    public String getDate(){
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick as done and empty as not done
    }

}
