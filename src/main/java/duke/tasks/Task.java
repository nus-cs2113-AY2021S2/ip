package duke.tasks;

public abstract class Task {
    protected String job;
    protected boolean isDone;
    
    public static boolean isFull = false;

    public Task() {
        this.job = null;
        this.isDone = false;
    }

    public Task(String inputJob) {
        this.job = inputJob;
        this.isDone = false;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
        output += this.job;

        output = addLabel(output);
        output = addEnd(output);

        System.out.println(output);
    }

    public abstract String addEnd(String s);
    public abstract String addLabel(String s);

}


