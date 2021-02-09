package Duke;

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
        printDescription();
    }

    public Task(String description, String time)  {
        this.description = description;
        this.isDone = false;
        this.time = "(" + time.substring(0, 2) + ":" + time.substring(2) + ")";
        taskCount++;
        printDescription();
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols ✘
    }

    public void setDone() {
        this.isDone = true;
        printDoneTask();
    }

    private void printDescription() {
        Class classType = this.getClass();
        System.out.println("**********************************************************");
        System.out.println("[" + classType.getName().charAt(0) + "]" + "[" + getStatusIcon() + "] " + this.description + this.time);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("**********************************************************");
    }

    private void printDoneTask() {
        Class classType = this.getClass();
        System.out.println("**********************************************************");
        System.out.println("Awesome! I have marked this task as done: ");
        System.out.println("[" + classType.getName().charAt(0) + "]" + "[" + getStatusIcon() + "] " + this.description);
        System.out.println("**********************************************************");
    }
}
