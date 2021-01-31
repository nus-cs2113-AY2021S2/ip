public class Task {
    private boolean isDone;
    private String description;
    private static int taskCounter = 0;

    public Task(String description){
        this.description = description;
        // default status for isDone is false
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon(){
        return (isDone ? "[ \u2713 ] " : "[ \u2718 ] ");
    }

    public static int getTaskCounter() {
        return taskCounter;
    }

    public static void incrementTaskCounter(){
        taskCounter++;
    }

    public static void decrementTaskCounter(){
        taskCounter--;
    }
    @Override
    public String toString(){
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
