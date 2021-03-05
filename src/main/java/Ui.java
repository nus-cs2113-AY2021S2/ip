import java.util.Scanner;

/**
 * Handles all user interactions with the user.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;
    private TaskList tasks;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Sets tasks to be used by the Ui class.
     *
     * @param tasks contains the list of tasks loaded from the save file.
     */
    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints an error message informing the user that there is a line of badly
     * formatted record in the save file.
     *
     * @param lineNum line number in the save file that contains the badly formatted record.
     */
    public void printBadTaskRecordFormatErrorMsg(int lineNum) {
        System.out.println("Bad task record format detected at line " + lineNum + " in save file! " +
                "Skipping this record...");
    }

    /**
     * Informs the user that a save file has been successfully created.
     *
     * @param saveFilePath absolute path of the newly created save file.
     */
    public void printCreatedSaveFileMsg(String saveFilePath) {
        System.out.println("Created a new save file called '" + saveFilePath + "'");
    }

    /**
     * Informs the user that Duke is unable to create a save file.
     *
     * @param saveFilePath absolute path of the save file that Duke fails to create.
     */
    public void printCreateSaveFileErrorMsg(String saveFilePath) {
        System.out.println(" OPPS! An I/O Error occurred when attempting to create the save file " +
                "'" + saveFilePath + "'!");
    }

    /**
     * Display the Ui banner and welcome message of Duke.
     */
    public void printWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(DIVIDER);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("Before we begin, do note that I am only able to accept lowercase commands.");
        System.out.println("Now, what can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Get a line of input as command from user.
     *
     * @return the String containing the command entered by user.
     */
    public String getCommandFromUser() {
        return scanner.nextLine().strip();
    }

    /**
     * Informs the user that Duke is unable to write into the save file.
     *
     * @param saveFilePath absolute path of the save file that Duke fails to write into.
     */
    public void printWritingSaveFileErrorMsg(String saveFilePath) {
        System.out.println(" OPPS! An I/O Error occurred when attempting to write into " +
                "'" + saveFilePath + "'!");
    }

    /**
     * Informs the user of the results of either a todo/deadline/event command.
     *
     * @param taskStored user provided task that has just been stored into the task list.
     * @param taskCount  number of tasks that had been stored into the task list.
     */
    public void printStoreTaskMsg(Task taskStored, int taskCount) {
        System.out.println(DIVIDER);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + taskStored);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Informs the user that the task list is empty.
     */
    public void printEmptyTaskListMsg() {
        System.out.println(DIVIDER);
        System.out.println(" You have no tasks in your list! :)");
        System.out.println(DIVIDER);
    }

    /**
     * Display the contents of the task list.
     */
    public void printStoredTasks() {
        System.out.println(DIVIDER);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currentTask = tasks.getTaskAt(i);
            System.out.printf(" %d.%s\n", (i + 1), currentTask);
        }
        System.out.println(DIVIDER);
    }

    /**
     * Informs the user that the requested task has been marked as done.
     *
     * @param taskMarked user requested task that has just been marked as done.
     */
    public void printMarkTaskAsDoneMsg(Task taskMarked) {
        System.out.println(DIVIDER);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskMarked);
        System.out.println(DIVIDER);
    }

    /**
     * Informs the user that the requested task has been deleted from the task list.
     * Also informs the user of the number of tasks left in the task list.
     *
     * @param deletedTask user requested task that has just been deleted from the task list.
     */
    public void printDeleteTaskMsg(Task deletedTask) {
        System.out.println(DIVIDER);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + deletedTask);
        System.out.println((tasks.isEmpty()) ? " Now you have no more tasks left in the list! :)" :
                " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Display the exit message of Duke.
     */
    public void printExitMsg() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * Printing of error messages caused by invalid command syntax or unknown commands
     * entered by the user.
     *
     * @param errorMsg error message containing user friendly explanation about the reason
     *                 of the error occurred.
     */
    public void printCommandErrorMsg(String errorMsg) {
        System.out.println(DIVIDER);
        System.out.println(" " + errorMsg);
        System.out.println(DIVIDER);
    }

    /**
     * Close the scanner used by Duke. This is the last method to be invoked by
     * Duke before shutdown.
     */
    public void closeScanner() {
        scanner.close();
    }
}

