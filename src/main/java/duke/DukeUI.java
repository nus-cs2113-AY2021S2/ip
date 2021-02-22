package duke;

import java.io.IOException;
import java.util.Scanner;

public class DukeUI {

    private static Scanner sc = new Scanner(System.in);
    private static String input;
    private static int currentCommand;

    public static void run() throws IOException {
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
                DukeController.listTasks();
                break;
            }
            case DukeCommands.EXIT:{
                DukeUI.printExitMessage();
                return;
            }
            case DukeCommands.DONE:{
                DukeController.markAsDone(input);
                break;
            }
            case DukeCommands.FIND:{
                DukeController.findKeyword(input);
                break;
            }
            case DukeCommands.ADD:{
                DukeController.addTask(input);
                break;
            }
            case DukeCommands.DELETE:{
                DukeController.deleteTask(input);
                break;
            }
            default: {
                System.out.println("Unknown error has occurred! Please try again.");
            }
            }
        }
    }

    public static void printWelcomeMessage(){
        System.out.printf("Hello! I'm Duke\n" +
                "What can I do for you?\n");
    }

    public static void printExitMessage(){
        System.out.printf("Bye. Hope to see you again soon!\n");
    }

    public static void printMenu() {
        System.out.printf("\n\n--------User Menu--------\n" +
                "list: list current tasks and completion status\n" +
                "done x: Mark task x as completed\n" +
                "todo TASK_DESCRIPTION\n" +
                "deadline TASK_DESCRIPTION /KEYWORD DATE_TIME\n" +
                "event TASK_DESCRIPTION /KEYWORD DATE_TIME\n" +
                "delete TASK_NUMBER\n" +
                "bye: Exit\n" +
                "(KEYWORD: any single word such as before, by, after...)\n\n");
    }
}
