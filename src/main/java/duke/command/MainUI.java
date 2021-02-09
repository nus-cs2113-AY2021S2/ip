package duke.command;

import duke.task.TaskManager;

import java.util.Scanner;

public class MainUI {
    public static final String LIST_COMMAND = "list";
    public static final String BYE_COMMAND = "bye";

    public static boolean isEnding = false;

    public static void displayUI(Scanner in){
        printWelcomeMessage();
        runProgram(in);
    }

    private static void runProgram(Scanner in) {
        while (!isEnding){
            String input = in.nextLine();
            switch (input){
            case LIST_COMMAND:
                TaskManager.printTaskList();
                break;
            case BYE_COMMAND:
                stopProgram();
                break;
            default:
                try {
                    TaskManager.handleTask(input);
                } catch (CommandNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        printDivider();
    }

    public static void stopProgram(){
        isEnding = true;
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
        System.exit(0);
    }

    public static void printDivider(){
        System.out.println("________________________________");
    }

}
