import java.util.Scanner;

public class DukeController {

    private static Task[] tasks = new Task[100];
    private static int currentTaskLength = 0;
    private static Scanner sc = new Scanner(System.in);
    private static String input;
    private static int taskIndex;
    private static final int INVALID_TASK_ID = -1;
    private static final int MINIMUM_DONE_COMMAND_LENGTH = 5;

    public static void run(){
        input = sc.nextLine();
        while(!input.equals("bye")){

            if(input.equals("list")){
                list();
                input = sc.nextLine();
                continue;
            } else if(input.length() > MINIMUM_DONE_COMMAND_LENGTH){
                taskIndex = validateDoneCommand();
                if(taskIndex != INVALID_TASK_ID){
                    markTaskAsDone();
                    input = sc.nextLine();
                    continue;
                }
            }
            addTask();
            input = sc.nextLine();
            continue;
        }
    }

    public static void list(){
        for(int i=0; i<currentTaskLength; i++){
            System.out.println(i+1 + ".["
                    + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());
        }
    }

    public static void markTaskAsDone(){
        tasks[taskIndex].setIsDone(true);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("["
                + tasks[taskIndex].getStatusIcon() + "] "
                + tasks[taskIndex].getDescription());
    }

    public static void addTask(){
        tasks[currentTaskLength] = new Task(input);
        System.out.println("added: " + tasks[currentTaskLength].getDescription());
        currentTaskLength++;
    }


}
