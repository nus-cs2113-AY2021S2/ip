public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("**********************************************************");
        System.out.println("Task added: " + description);
        System.out.println("**********************************************************");
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : ""); //return tick or X symbols ✘
    }

    public void doneTask(){
        this.isDone = true;
        System.out.println("**********************************************************");
        System.out.println("Awesome! I have marked this task as done: ");
        System.out.println("[" + getStatusIcon() + "] " + this.description);
        System.out.println("**********************************************************");
    }
}

/*public class Task {
    private static String[] taskList = new String[100];
    private static int taskCount = 0;
    private String task;

    public Task(String task){
        this.task=task;
        taskList[taskCount] = task;
        taskCount++;
        System.out.println("**********************************************************");
        System.out.println("I have added: \"" + task + "\" into your task list! :)");
        System.out.println("**********************************************************");
    }

    public static void getList(){
        System.out.println("**********************************************************");
        for(int i=0; i<taskCount; ++i){
            System.out.println(i+1 + ". " + taskList[i]);
        }
        System.out.println("**********************************************************");
    }

    public static int getTaskCount(){return taskCount;}

}*/
