public class Task {
    protected String description;
//    protected String taskType = "T";
    protected boolean isDone;
    private static int taskCount=0;

    public static int getTaskCount() {
        return taskCount;
    }

    public static void setTaskCount(int taskCount) {
        Task.taskCount = taskCount;
    }


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void markAsDone(){
        isDone = true;
        System.out.println(this.getStatusIcon()+" "+this.description);
    }
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() +"]" + this.description;
    }

    public void loadStatus(String s) {
        if (s.equals("\u2713"))
            isDone = true;
    }

    public static int updateTaskCount() {
        taskCount--;
        return taskCount;
    }
}
