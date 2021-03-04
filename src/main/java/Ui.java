import java.util.Scanner;

/**
 * Contains all the methods for user interaction
 */
public class Ui {

    static Scanner scanner = new Scanner(System.in);
    static String LINEBREAK = "____________________________________________________________\n";
    static String LOGO = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String GREETING =  " Hello! I'm Duke\n"
            + " What can I do for you?\n";
    static String GOODBYE = "Bye, Hope to see you again soon!\n";
    static String TYPEHELP = "Type \"help\" to see the list of commands!\n";

    /**
     * Prints a message for when the command is unrecognized
     */
    public static void unrecognizedCommandMessage() {
        System.out.println("OOPS! I cant recognize that command! " + TYPEHELP);
    }

    /**
     * Prints a message for when the command is improperly formatted
     */
    public static void invalidFormatMessage() {
        System.out.println("OOPS! Please check to see if your command is properly formatted! " + TYPEHELP);
    }

    /**
     * Prints a message for when the task has been successfully added
     *
     * @param inputTask task to be added
     * @param listSize current size of the task list
     */
    public static void taskAddedMessage(Task inputTask, int listSize) {
        System.out.println("Got it. I've added this task: \n" + inputTask.toString());
        System.out.println("Now you have " + listSize + " tasks in the list");
    }

    /**
     * Prints a message for when the list is empty
     */
    public static void emptyListMessage() {
        System.out.println("OOPS! Looks like you have nothing in your list!");
    }

    /**
     * Prints a message for when the list is not empty
     */
    public static void notEmptyListMessage() {
        System.out.println("Here are the tasks in your list!");
    }

    /**
     * Prints an item in the list
     *
     * @param i current index of item being printed
     * @param toString the task to be printed
     */
    public static void printListItems(int i, String toString) {
        System.out.println(i + 1 + ". " + toString);
    }

    /**
     * Prints a message for when a task is marked done
     *
     * @param toString task that has been marked as done
     */
    public static void markAsDoneMessage(String toString) {
        System.out.println("Nice! I've marked this task as done: \n" + toString);
    }

    /**
     * Prints a message for when a task has been deleted
     *
     * @param toString deleted task
     * @param index current size of the task list
     */
    public static void deleteTaskMessage(String toString, int index) {
        System.out.println("Noted. I've removed this task: \n" + toString);
        System.out.println("Now you have " + index + " tasks in the list");
    }

    /**
     * Prints list of commands for duke
     */
    public void showHelp() {
        System.out.println("Here are the list of available commands: ");
        System.out.println("\"help\": Brings up this menu!");
        System.out.println("\"todo [action]\": adds a todo to the list!");
        System.out.println("\"deadline [action] /by [time]\": adds a deadline to the list!");
        System.out.println("\"event [action] /at [time]\": adds an event to the list!");
        System.out.println("\"list\": Brings up the list of all the items you stored!");
        System.out.println("\"delete [index in list]\": deletes an items from the list!");
        System.out.println("\"done [index in list]\": marks a task in your list as done!");
        System.out.println("\"find [keyword to find]\": finds a keyword or phrase in your list!");
        System.out.println("\"bye\": exits duke");
        System.out.println("Duke ONLY saves when you EXIT the program!");
    }

    /**
     * Prints message for when there is loading errors
     */
    public void showLoadingError() {
        System.out.println("OOPS! There was an error loading the file!");
    }

    /**
     * Prints a welcome message
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(LINEBREAK + GREETING + LINEBREAK);
    }

    /**
     * Prints a goodbye message
     */
    public void showGoodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Reads in the user command from the command line interface
     *
     * @return user input string
     */
    public String readCommand() {
        String input = scanner.nextLine().trim();
        return input;
    }

    /**
     * Prints a line in the output
     */
    public void showLine() {
        System.out.print(LINEBREAK);
    }

    /**
     * Prints a message for items found in the list
     */
    public static void foundListMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints out individual tasks that have been found
     *
     * @param i index of current task printed
     * @param toString current task being printed
     */
    public static void foundTasks(int i, String toString) {
        System.out.println(i + "." + toString);
    }

    /**
     * Prints a message for if no items are found
     */
    public static void noMatchingTasks() {
        System.out.println("OOPS! Looks like there are no matching tasks!");
    }
}