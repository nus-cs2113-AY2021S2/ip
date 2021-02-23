package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    
    public static boolean isFull = false;

    public Task() {
        this.description = null;
        this.isDone = false;
    }

    public Task(String inputJob) {
        this.description = inputJob;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void printTask() {
        String doneBox = "[X] ";
        String emptyBox = "[ ] ";

        String output = this.isDone ? doneBox : emptyBox;
        output += this.description;

        output = addLabel(output);
        output = addEnd(output);

        System.out.println(output);
    }

    public abstract String addEnd(String s);
    public abstract String addLabel(String s);

}


