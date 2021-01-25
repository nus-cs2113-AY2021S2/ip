public class List {
    private static String[] taskList = new String[100];
    private static int taskCount = 0;
    private String task;

    public List(String task){
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

}
