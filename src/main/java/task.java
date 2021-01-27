public class task {
    protected String description;
    protected boolean isDone;

    public task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone(){
        this.isDone = true;
    }
}
