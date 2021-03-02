package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String time;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.time = "";
        taskCount++;
    }

    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time;
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols ✘
    }

    public String getAlphabet() {
        return "T";
    }

    public void setDone() {
        this.isDone = true;
    }

    public void printDescription() {
        System.out.println("**********************************************************");
        System.out.println("[" + this.getAlphabet() + "]" + "[" + getStatusIcon() + "] " + this.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("**********************************************************");
    }

    public void printDoneTask() {
        System.out.println("**********************************************************");
        System.out.println("Awesome! I have marked this task as done: ");
        System.out.println("[" + this.getAlphabet() + "]" + "[" + getStatusIcon() + "] " + this.toString());
        System.out.println("**********************************************************");
    }
}
