public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(){};

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void markAsDone() {
        if (isDone == false) {
            isDone = true;
            System.out.println("Nice! I've marked this task as done: ");
        }
        else {
            System.out.println("This task is already marked as done.");
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getPrintedLine(){
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

}