import java.util.Scanner;
public class TaskList {
    public static String[] tasks;
    public static int index = 0;

    public TaskList(){
        tasks = new String[100];
    }

    public static void addTask(String task){
        Scanner sc = new Scanner(System.in);
        System.out.println("Added: " + task.trim());
        tasks[index] = task;
        index++;
    }

    public void showTaskList(){
        System.out.print("Here are the tasks in your list: \n");
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ": " + tasks[i]);
        }
    }

}
