package Duke;
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

    public static String[] getUserInput() {
        String userInput = scanner.nextLine();
        return DukeParser.splitInputIntoString(userInput);
    }

    private static String[] splitInputIntoString(String userInput) {
        String[] listOfInputs = userInput.split(" ", 2);
        if (listOfInputs.length == 1) {
            listOfInputs = new String[]{userInput, "filler"};
        }
        return listOfInputs;
    }

    public static void printWelcomeMessage() {
        print("Hello from\n" + LOGO);
        printLine();
        print("Hello! I'm Duke");
        print("What can I do for you?");
    }

    public static void printExitingMessage() {
        print("Bye. Hope to see you again soon!");
    }

    public static void notifyUserNewTask(Task selectedTask, TaskManager currentTaskManager) {
        DukeUI.print("Got it. I've added this task:");
        DukeUI.print("  " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        DukeUI.print("Now you have " + currentTaskManager.taskCount() + " tasks in the list.");
    }
}
