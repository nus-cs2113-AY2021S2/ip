package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Manages all interactions between the user and the system.
 */
public class DukeUI {

    private static Scanner sc = new Scanner(System.in);
    private static String input;
    private static int currentCommand;

    /**
     * Prompts the user for inputs, provide feedbacks,
     * and pass controls to other parts of the program depending
     * on the user's inputs.
     *
     * @throws IOException If a particular file is not found in the database.
     */
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

    /**
     * Displays a welcome message when the user just launched the software.
     */
    public static void printWelcomeMessage(){
        System.out.printf("Hello! I'm Duke\n" +
                "What can I do for you?\n");
    }

    /**
     * Displays a termination message when the user is about to close the program.
     */
    public static void printExitMessage(){
        System.out.printf("Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays a menu that provides simple guide to the user on how to use the software.
     */
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
