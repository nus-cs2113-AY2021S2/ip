import java.util.Scanner;

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

    public static void unrecognizedCommandMessage() {
        System.out.println("OOPS! I cant recognize that command! " + TYPEHELP);
    }

    public static void invalidFormatMessage() {
        System.out.println("OOPS! Please check to see if your command is properly formatted! " + TYPEHELP);
    }

    public static void taskAddedMessage(Task inputTask, int listSize) {
        System.out.println("Got it. I've added this task: \n" + inputTask.toString());
        System.out.println("Now you have " + listSize + " tasks in the list");
    }

    public static void emptyListMessage() {
        System.out.println("OOPS! Looks like you have nothing in your list!");
    }

    public static void notEmptyListMessage() {
        System.out.println("Here are the tasks in your list!");
    }

    public static void printListItems(int i, String toString) {
        System.out.println(i + 1 + ". " + toString);
    }

    public static void markAsDoneMessage(String toString) {
        System.out.println("Nice! I've marked this task as done: \n" + toString);
    }

    public static void deleteTaskMessage(String toString, int index) {
        System.out.println("Noted. I've removed this task: \n" + toString);
        System.out.println("Now you have " + index + " tasks in the list");
    }

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
    }

    public void showLoadingError() {
        System.out.println("OOPS! There was an error loading the file!");
    }

    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(LINEBREAK + GREETING + LINEBREAK);
    }

    public void showGoodbye() {
        System.out.println(GOODBYE);
    }

    public String readCommand() {
        String input = scanner.nextLine().trim();
        return input;
    }

    public void showLine() {
        System.out.print(LINEBREAK);
    }

    public static void foundListMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public static void foundTasks(int i, String toString) {
        System.out.println(i + "." + toString);
    }

    public static void noMatchingTasks() {
        System.out.println("OOPS! Looks like there are no matching tasks!");
    }
}
