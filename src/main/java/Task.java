public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTask() {
        return description;
    }

    public String listTask() {
        String str = "[";
        if (isDone) {
            //System.out.print(getStatusIcon() + "] " + description);
            str = str + getStatusIcon() + "] " + description;
        } else {
            //System.out.print(" ] " + description);
            str = str + " ] " + description;
        }
        return str;
    }
}
