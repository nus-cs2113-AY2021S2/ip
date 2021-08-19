package Duke.UI;
import Duke.Parser.DukeParser;
import Duke.Parser.TodoException;
import Duke.Task.Task;
import Duke.TaskManager.TaskManager;

import java.util.Scanner;


public class DukeUI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printLine() {
        System.out.println("-------------------------------------------");
    }

    public static void print(String output) {
        System.out.println(output);
    }

    /**
     * Get userInput from the scanner
     *
     * @return return userInput as String
     */
    public static String[] getUserInput() {
        String userInput = scanner.nextLine();
        return DukeParser.splitInputIntoString(userInput);
    }

    /**
     * Print Welcome Message
     */
    public static void printWelcomeMessage() {
        print("Hello from\n" + LOGO);
        printLine();
        print("Hello! I'm Duke.Duke");
        print("What can I do for you?");
    }

    /**
     * Print exiting message
     */
    public static void printExitingMessage() {
        print("Bye. Hope to see you again soon!");
    }

    public static void notifyUserNewTask(Task selectedTask, TaskManager currentTaskManager) {
        DukeUI.print("Got it. I've added this task:");
        DukeUI.print("  " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        DukeUI.print("Now you have " + currentTaskManager.taskCount() + " tasks in the list.");
    }

    public static void informErrorToUser(TodoException e) {
        DukeUI.printLine();
        DukeUI.print(e.sendErrorMessage());
    }
}
