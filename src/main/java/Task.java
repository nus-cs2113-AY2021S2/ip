public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        printDescription();
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : ""); //return tick or X symbols ✘
    }

    public void doneTask(){
        this.isDone = true;
        printDoneTask();
    }

    private void printDescription() {
        System.out.println("**********************************************************");
        System.out.println("Task added: " + this.description);
        System.out.println("**********************************************************");
    }

    private void printDoneTask() {
        System.out.println("**********************************************************");
        System.out.println("Awesome! I have marked this task as done: ");
        System.out.println("[" + getStatusIcon() + "] " + this.description);
        System.out.println("**********************************************************");
    }
}
