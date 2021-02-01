public class Task {
    private String job;
    private boolean isDone;

    public static int taskCount = 0;

    public Task() {
        this.job = null;
        this.isDone = false;
    }

    public Task(String inputJob){
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

    public void printTask(){
        String doneBox = "[X] ";
        String emptyBox = "[ ] ";

        String output = this.isDone ? doneBox : emptyBox;
        output += this.job;

        output = addLabel(output);

        System.out.println(output);
    }

    public String addLabel(String s){
        String label = "[*]";
        label += s;
        return label;
    }
}
