public class Task {
    protected String description;
    protected boolean isDone;
    private static String[] taskList = new String[100];
    private static int taskCount = 0;

    // Task Constructor
    public Task(String description){
        this.description = description;
        System.out.println("after desc");
        this.isDone = false;
        System.out.println("after isDone");
    }

    // Add Task to taskList and increment taskCount
    public void addTask(String description){
        taskList[taskCount] = description;
        taskCount++;
    }

    public static void listTasks(){
        int index = 1;
        for(String t : taskList){
            System.out.println(Integer.toString(index) + ". " + t);
            index++;
            if(!(taskCount > index - 1)){
                break;
            }
        }
    }
}
