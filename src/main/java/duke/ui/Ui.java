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

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Lists out the commands that the user can use.
     *
     * @return List of Commands.
     */
    public static void printHelpCommandList() {
        System.out.println("\tHere are the commands you can use:\n" +
                "\t\t+ To add tasks:\n" +
                "\t\t\t\"todo <todo task name>\"\n" +
                "\t\t\t\"deadline <deadline task name> /by <YYYY-MM-DD HH:mm>\"\n" +
                "\t\t\t\"event <event task name> /at <YYYY-MM-DD HH:mm>\"\n\n" +
                "\t\t- To delete tasks:\n" +
                "\t\t\t\"delete <index of task>\"\n\n" +
                "\t\t✓ To mark tasks as done:\n" +
                "\t\t\t\"done <index of task>\"\n\n" +
                "\t\t= To list the tasks on your task list:\n" +
                "\t\t\t\"list\"\n\n" +
                "\t\tỎ To find tasks on your task list:\n" +
                "\t\t\t\"find <keyword>\"\n\n" +
                "\t\t₿ To exit Duke:\n" +
                "\t\t\t\"bye\"");
    }

    /**
     * Greets the user upon entering Duke.
     *
     * @return Greetings.
     */
    public void printGreeting() {
        System.out.print(LOGO);
        System.out.print("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public static void printSearchWordNotFoundMessage() {
        System.out.println("\tNo matches found! Please try another keyword.");
    }

    public static void printTask(ArrayList<Task> tasks, int taskNumber) {
        System.out.println("\t" + (taskNumber + 1) + "." +
                tasks.get(taskNumber).toString());
    }

    public static void printAddedTaskMessage(Task currentTask) {
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

    public static void printRemainingTaskMessage(ArrayList<Task> tasks) {
        System.out.print("\tNow you have " + tasks.size() + checkSingular(tasks) + "in your list.\n");
    }

    public static void printMarkedTaskMessage(Task currentTask) {
        System.out.print("\tNice! I've marked this task as done:" +
                "\n\t" + currentTask.toString() + "\n");
    }

    /**
     * Exits Duke.
     *
     * @return Exit output.
     */
    public void printExitMessage() {
        System.out.print("Bye. Hope to see you again soon!\n");
    }


}
