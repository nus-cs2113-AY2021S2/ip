package duke.ui;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Prints relevant message to prompt user input
 */
public class Ui {
    private static final String DIVIDER = "\t_______________________________\n";
    private static String LOGO =
            "▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
                    + "▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\n"
                    + "▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\n"
                    + "▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\n"
                    + "▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\n"
                    + "▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\n"
                    + "▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\n"
                    + "▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\n"
                    + "▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\n"
                    + "▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█\n"
                    + " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printPresentDirectory() {
        System.out.println("Present project directory is: " + System.getProperty("user.dir"));
    }

    public static void printSuccessfulCreateFolderMessage() {
        System.out.println("Folder created successfully.");
    }

    public static void printFolderExistsMessage() {
        System.out.println("Folder already exists.");
    }

    public static void printErrorMessage(IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    public static void printFileExistsMessage() {
        System.out.println("Text file already exists.");
    }

    public static void printDivider(){
        System.out.println(DIVIDER);
    }
    public void printGreeting() {
        System.out.print(LOGO);
        System.out.print("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public static void printSearchWordNotFoundMessage() {
        System.out.println("No matches found! Please try again.");
    }

    public static void printTask(ArrayList<Task> tasks, int taskNumber){
        System.out.println("\t" + (taskNumber + 1) + "." +
                tasks.get(taskNumber).toString());
    }

    public static void printAddedTaskMessage(Task currentTask){
        System.out.println("\tGot it. I've added this task:\n\t\t" +
                currentTask.toString());
    }
    /**
     * Checks number of tasks for printing fluency
     */
    protected static String checkSingular(ArrayList<Task> tasks) {
        if (tasks.size() > 1) {
            return " tasks ";
        }
        return " task ";
    }
    public static void printRemainingTaskMessage(ArrayList<Task> tasks){
        System.out.print("\tNow you have " + tasks.size() + checkSingular(tasks) + "in your list.\n");
    }

    public static void printMarkedTaskMessage(Task currentTask){
        System.out.print("\tNice! I've marked this task as done:" +
                "\n\t" + currentTask.toString() + "\n");
    }

    public void printExitMessage() {
        System.out.print("Bye. Hope to see you again soon!\n");
    }


}
