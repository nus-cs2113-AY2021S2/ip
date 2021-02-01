import java.util.Scanner;

public class DukeController {

    private static Task[] tasks = new Task[100];
    private static int currentTaskLength = 0;
    private static Scanner sc = new Scanner(System.in);
    private static String input;
    private static int currentCommand;

    public static void run(){
        while (true){
            input = sc.nextLine();
            currentCommand = DukeCommandValidator.getCommand(input);

            switch (currentCommand){
            case DukeCommands.ADD:{
                addTask();
                break;
            }
            case DukeCommands.LIST:{
                list();
                break;
            }
            case DukeCommands.EXIT:{
                return;
            }
            case DukeCommands.DONE:{
                done();
                break;
            }
            default: {
                System.out.println("Unknown error has occurred! Please try again.");
            }
            }
        }
    }

    public static void list(){
        for(int i=0; i<currentTaskLength; i++){
            System.out.println(i+1 + ".["
                    + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());
        }
    }

    public static void done(){
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if(taskIndex >= currentTaskLength || taskIndex < 0){
            System.out.println("The task index you have entered is out of bound!");
            return;
        }
        else{
            tasks[taskIndex].setIsDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("["
                    + tasks[taskIndex].getStatusIcon() + "] "
                    + tasks[taskIndex].getDescription());
        }

    }

    public static void addTask(){
        tasks[currentTaskLength] = new Task(input);
        System.out.println("added: " + tasks[currentTaskLength].getDescription());
        currentTaskLength++;

    }

}
