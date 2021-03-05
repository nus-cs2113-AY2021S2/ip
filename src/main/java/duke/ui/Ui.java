package duke.ui;

import duke.tasks.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

/**
 * Represents user interaction messages with user in the user interface.
 */
public class Ui {

    public static final String WELCOME_MESSAGE = "\n" +
            "██╗░░██╗███████╗██╗░░░░░██╗░░░░░░█████╗░\n" +
            "██║░░██║██╔════╝██║░░░░░██║░░░░░██╔══██╗\n" +
            "███████║█████╗░░██║░░░░░██║░░░░░██║░░██║\n" +
            "██╔══██║██╔══╝░░██║░░░░░██║░░░░░██║░░██║\n" +
            "██║░░██║███████╗███████╗███████╗╚█████╔╝\n" +
            "╚═╝░░╚═╝╚══════╝╚══════╝╚══════╝░╚════╝░\n" + "from\n" + "\n" +
            "██████╗░██╗░░░██╗██╗░░██╗███████╗\n" +
            "██╔══██╗██║░░░██║██║░██╔╝██╔════╝\n" +
            "██║░░██║██║░░░██║█████═╝░█████╗░░\n" +
            "██║░░██║██║░░░██║██╔═██╗░██╔══╝░░\n" +
            "██████╔╝╚██████╔╝██║░╚██╗███████╗\n" +
            "╚═════╝░░╚═════╝░╚═╝░░╚═╝╚══════╝\n";

    public static final String GOODBYE_MESSAGE = "\n" +
            "░██████╗░░█████╗░░█████╗░██████╗░██████╗░██╗░░░██╗███████╗\n" +
            "██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝\n" +
            "██║░░██╗░██║░░██║██║░░██║██║░░██║██████╦╝░╚████╔╝░█████╗░░\n" +
            "██║░░╚██╗██║░░██║██║░░██║██║░░██║██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
            "╚██████╔╝╚█████╔╝╚█████╔╝██████╔╝██████╦╝░░░██║░░░███████╗\n" +
            "░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚═════╝░░░░╚═╝░░░╚══════╝\n" + "Hope to see you again soon!";
    public static final String LINE = "_________________________________________________________________________";

    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints the exit messages at the end of the program.
     */
    public static void printGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Prints the welcome messages at the start of the program.
     */
    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints error message to inform user about invalid input.
     *
     * @param type if identified, would be task type. If unidentified, would be user input.
     */
    public static void printInvalidMessage(String type) {
        System.out.println("\uD83D\uDE1E OOPS! " + type + " is not valid, please try again!");
    }

    /**
     * Prints the task in the parameter
     *
     * @param taskToPrint task to be printed
     */
    public static void printTask(Task taskToPrint) {
        System.out.print(taskToPrint.getTaskType() + taskToPrint.getStatusIcon() + " " + taskToPrint.getDescription());
        if (taskToPrint.getTaskType().equals("[D]")) {
            System.out.print(" (by:" + taskToPrint.getDateTime() + ")");
        } else if (taskToPrint.getTaskType().equals("[E]")) {
            System.out.print(" (at:" + taskToPrint.getDateTime() + ")");
        }
        System.out.print("\n");
    }

    /**
     * Prints the number of existing tasks
     */
    public static void printTaskSize() {
        if (TaskList.tasks.size() > 1) {
            System.out.println("Now you have " + TaskList.tasks.size() + " tasks in the list.");
        } else if (TaskList.tasks.size() == 1) {
            System.out.println("Now you have " + TaskList.tasks.size() + " task in the list.");
        } else {
            System.out.println("You don't have any tasks in the list");
        }
    }

    /**
     * Prints the tasks found by the FindCommand
     *
     * @param foundTasks ArrayList of tasks that matches with keyword input by user.
     */
    public static void printFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println("\uD83D\uDE1E No tasks found with given keyword. Please try again");
            return;
        }
        System.out.println("Here are the matching tasks in your list!");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.print((i + 1) + ". ");
            Task currentTask = foundTasks.get(i);
            printTask(currentTask);

        }
    }
}
