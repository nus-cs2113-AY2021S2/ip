import java.util.Scanner;

/**
 * Manages the text ui interface
 */

public class Ui {

    public static final String LINE_STRING = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    private void printLine() {
        System.out.println(LINE_STRING);
    }

    /**
     * Prints when a new Task is created
     * @param size Size of the task list
     */
    public void printAddToList(int size) {
        printLine();
        System.out.println("Say no more fam. The task is added:\n  [✔]");
        System.out.println(size + " task(s) in the list.");
        printLine();
    }

    /**
     * Prints every task in the task list with the task details
     * @param tasks The task list being printed
     */
    public void printList(TaskList tasks) {
        printLine();
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i+1) + "." + tasks.getStatus(i));
        }
        printLine();
    }

    /**
     * Prints when a task is marked as complete
     */
    public void printCompleteTask() {
        printLine();
        System.out.println("Task.Task marked as done, gg ez");
        System.out.println("  [✔]");
        printLine();
    }

    /**
     * Prints when a task is deleted
     * @param status Checkbox status of the task
     * @param size Size of the task list
     */
    public void printDeleteTask(String status, int size) {
        printLine();
        System.out.println("You are a quitter 👎 Anyways, I removed this:");
        System.out.println("  " + status);
        System.out.println(size + " task(s) left in the list.");
        printLine();
    }

    /**
     * Prints a list of task found to match the query
     * @param results Task list of tasks that match the query
     * @param query The string that the user is searching for
     */
    public void printFindTask(TaskList results, String query) {
        printLine();
        System.out.println("Here are the tasks in your list that match「" + query + "」:");
        for (int i = 0; i < results.getSize(); i++) {
            System.out.println((i+1) + "." + results.getStatus(i));
        }
        printLine();
    }

    /**
     * Prints when no tasks are found in a task list
     */
    public void printNoResultsFound() {
        printLine();
        System.out.println("No tasks found!");
        printLine();
    }

    /**
     * Prints when Bob is started
     */
    public void printWelcome() {
        String welcome = LINE_STRING +
                "\n Hello! I'm Bob 😀\n" +
                " If you need anything hit me up fam 😌\n" +
                LINE_STRING;
        System.out.println(welcome);
    }

    /**
     * Prints when Bob closes
     */
    public void printGoodbye() {
        String goodbye = LINE_STRING + "\n Chao 👌\n" + LINE_STRING;
        System.out.println(goodbye);
    }

    /**
     * Prints when the time marker is missing for Deadline or Event
     * @param commandType Command enum parsed by scanSwitch
     */
    public void printNoCommandFormat(Command commandType) {
        String commandName = commandType.name().toLowerCase();
        String exceptionMessage = LINE_STRING +
                "\n 😥 You gotta use the time marker for " + commandName + "\n" +
                "and the time which it happens\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    /**
     * Prints when creating a task without a label
     * @param commandType Command enum parsed by scanSwitch
     */
    public void printNoTaskLabel(Command commandType) {
        String commandName = commandType.name().toLowerCase();
        String exceptionMessage = LINE_STRING +
                "\n 😥 You gotta tell me what is the task for " + commandName + "\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    /**
     * Prints when input doesn't match any command
     */
    public void printNoSuchMethod() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 I don't quite get what the command means, \n" +
                "type 'help' for a list of commands\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printIOException() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 IO issue encountered! Unable to read file\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printNumberFormatException() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 Number format exception encountered! Input may be corrupted\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printNoTaskSpecified() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 Tell me what task are you referring to\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printIndexOutOfBounds() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 Index out of bounds\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printNoSearchQuery() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 Please tell me what you are finding for\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printHelp() {
        String helpMessage = LINE_STRING +
                "\n Available commands:"
                + "\n   todo taskLabel"
                + "\n   deadline deadlineLabel /by time"
                + "\n   event eventLabel /at time"
                + "\n   list"
                + "\n   done taskNumber"
                + "\n   find query"
                + "\n   delete taskNumber"
                + "\n   help"
                + "\n   bye\n"
                + LINE_STRING;
        System.out.println(helpMessage);
    }
}
