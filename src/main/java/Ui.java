import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private Scanner scanner;
    private TaskList tasks;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    public void printBadTaskRecordFormatErrorMsg(int lineNum) {
        System.out.println("Bad task record format detected at line " + lineNum + " in save file! " +
                "Skipping this record...");
    }

    public void printCreatedSaveFileMsg(String saveFilePath) {
        System.out.println("Created a new save file called '" + saveFilePath + "'");
    }

    public void printCreateSaveFileErrorMsg(String saveFilePath) {
        System.out.println(" OPPS! An I/O Error occurred when attempting to create the save file " +
                "'" + saveFilePath + "'!");
    }

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

    public String getCommandFromUser() {
        return scanner.nextLine().strip();
    }

    public void printWritingSaveFileErrorMsg(String saveFilePath) {
        System.out.println(" OPPS! An I/O Error occurred when attempting to write into " +
                "'" + saveFilePath + "'!");
    }

    public void printStoreTaskMsg(Task taskStored, int taskCount) {
        System.out.println(DIVIDER);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + taskStored);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void printEmptyTaskListMsg() {
        System.out.println(DIVIDER);
        System.out.println(" You have no tasks in your list! :)");
        System.out.println(DIVIDER);
    }

    public void printStoredTasks() {
        System.out.println(DIVIDER);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currentTask = tasks.getTaskAt(i);
            System.out.printf(" %d.%s\n", (i + 1), currentTask);
        }
        System.out.println(DIVIDER);
    }

    public void printMarkTaskAsDoneMsg(Task taskMarked) {
        System.out.println(DIVIDER);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskMarked);
        System.out.println(DIVIDER);
    }

    public void printDeleteTaskMsg(Task deletedTask) {
        System.out.println(DIVIDER);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + deletedTask);
        System.out.println((tasks.isEmpty()) ? " Now you have no more tasks left in the list! :)" :
                " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void printExitMsg() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public void printCommandErrorMsg(String errorMsg) {
        System.out.println(DIVIDER);
        System.out.println(" " + errorMsg);
        System.out.println(DIVIDER);
    }

    public void closeScanner() {
        scanner.close();
    }
}

