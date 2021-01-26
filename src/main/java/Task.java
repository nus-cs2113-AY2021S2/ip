public class Task {
    protected String description;
    private static boolean isDone;
    private static String[] taskList = new String[100];
    private static boolean[] isDoneList = new boolean[100];
    private static int taskCount = 0;

    // Task Constructor
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public static String getStatusIcon(int index){
        return(isDoneList[index] ? "\u2713" : " ");
    }

    public static void markAsDone(String index){
        isDoneList[Integer.parseInt(index)-1] = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + "[" + getStatusIcon(Integer.parseInt(index)-1) + "] " + taskList[Integer.parseInt(index)-1]);
    }

    // Add Task to taskList and increment taskCount
    public void addTask(String description){
        taskList[taskCount] = description;
        isDoneList[taskCount] = this.isDone;
        taskCount++;
    }

    public static void listTasks(){
        int index = 1;
        for(String t : taskList){
            System.out.print("");
            System.out.println(Integer.toString(index) + ".[" + getStatusIcon(index-1) + "] " + t);
            index++;
            if(!(taskCount > index - 1)){
                break;
            }
        }
    }
}
