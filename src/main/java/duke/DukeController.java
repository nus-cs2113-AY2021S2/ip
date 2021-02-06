package duke;

import duke.Deadline;
import duke.DukeCommandValidator;
import duke.DukeCommands;

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
            case DukeCommands.INVALID_COMMAND:{
                System.out.println("Please enter a valid command!");
                DukeUI.printMenu();
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
            case DukeCommands.ADD:{
                add();
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
            System.out.printf("%d.", i+1);
            tasks[i].printTask();
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

    public static void add(){
        if(DukeCommandValidator.isToDoValid(input)==true){
            tasks[currentTaskLength] = new ToDo(input);
        }else if(DukeCommandValidator.isDeadlineValid(input)==true){
            tasks[currentTaskLength] = new Deadline(input);;
        }else if(DukeCommandValidator.isEventValid(input)){
            tasks[currentTaskLength] = new Event(input);
        }else{
            System.out.println("Invalid syntax for add commands! Please try again!");
            DukeUI.printMenu();
            return;
        }
        System.out.printf("Got it. I've added this task:\n  ");
        tasks[currentTaskLength].printTask();
        System.out.printf("Now you have %d tasks in the list.\n", currentTaskLength + 1);
        currentTaskLength++;
    }

}
