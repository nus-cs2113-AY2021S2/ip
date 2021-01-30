public class Task {
    private static final int MAX_NUMBER_OF_TASKS = 100;
    protected String description;
    private boolean isDone;
    private static Task[] taskList = new Task[MAX_NUMBER_OF_TASKS];
    private static int taskCount = 0;
    private static String time;

    // Task Constructor
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return(this.isDone ? "x" : " ");
    }

    public static void markAsDone(String i){
        int index = Integer.parseInt(i);
        Task t = taskList[index-1];
        t.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        t.printTaskInformation();
        System.out.println("");
    }

    // Add Task to taskList and increment taskCount
    public void addTask(){
        System.out.println("Got it, I've added this task:");
        System.out.print("  ");
        this.printTaskInformation();
        System.out.println("");
        taskList[taskCount]= this;
        taskCount++;
        if(taskCount == 1) {
            System.out.println("Now you have " + taskCount + " task in the list.");
        }
        else{
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }

    public static void listTasks(){
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        for(Task t : taskList){
            printIndex(index);
            t.printTaskInformation();
            System.out.println("");
            index++;
            if(!(taskCount > index - 1)){
                break;
            }
        }
    }

    private static void printIndex(int index) {
        System.out.print(Integer.toString(index) + ".");
    }

    public void printTaskInformation(){
        this.printTaskType();
        this.printStatusIcon();
        this.printTaskName();
    }

    public String getTaskType(){
        return "N/A";
    }

    private void printTaskType(){
        System.out.print("[" + this.getTaskType() + "]");
    }

    private void printStatusIcon(){
        System.out.print("[" + this.getStatusIcon() + "] ");
    }

    private void printTaskName(){
        System.out.print(this.description);
    }
}
