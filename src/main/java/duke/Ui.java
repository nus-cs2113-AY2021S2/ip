package duke;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Print welcome message when user launches the application.
     */
    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Read in user's input as string.
     * @return User's input as string
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    /**
     * Print divider line.
     */
    public static void showLine() {
        System.out.println("------------------------------------------");
    }

    /**
     * Print exit message when the user exits the application.
     * Set the boolean variable indicating whether user wishes to exit the application = true.
     * @param isExit Boolean variable indicating whether user wishes to exit the application
     */
    public static void printExitMessage(boolean isExit) {
        System.out.println("    Bye. Hope to see you again soon!");
        isExit = true;
    }

    /**
     * Print all the tasks in the 'tasks' arraylist.
     * @param tasks ArrayList containing all the tasks
     */
    public static void printTasks(ArrayList<Task> tasks) {
        for (int i=0; i<tasks.size(); i++) {
            System.out.println("    " + (i+1) + ". " + tasks.get(i));
        }
    }

    /**
     * Print message for user's command 'list'.
     */
    public static void printTaskList() {
        System.out.println("    Here are the tasks in your list: ");
        printTasks(TaskList.getTasks());
        showLine();
    }

    /**
     * Print message for user's command 'find'.
     * @param task ArrayList containing all the matching tasks
     */
    public static void printMatchingTasks(String task) {
        System.out.println("    Here are the matching tasks in your list: ");
        printTasks(TaskList.findTask(task));
        showLine();
    }

    /**
     * Print message for adding task.
     * @param tasks ArrayList containing all the tasks
     * @param tasksCount The number of tasks in the arraylist
     */
    public static void printAddTaskMessage(ArrayList<Task> tasks, int tasksCount) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("      " + tasks.get(tasksCount));
        System.out.println("    Now you have " + (tasksCount + 1) + " tasks in the list.");
        showLine();
    }

    /**
     * Print message for marking task done.
     * @param task Task object to be mark done
     */
    public static void printMarkTaskDoneMessage(Task task) {
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + task);
        showLine();
    }

    /**
     * Print message for deleting a task.
     * @param task Task object to be deleted
     * @param tasksCount The number of tasks in the arraylist
     */
    public static void printDeleteTaskMessage(Task task, int tasksCount) {
        System.out.println("    Noted. I've removed this task: ");
        System.out.println("    " + task);
        System.out.println("    Now you have " + tasksCount + " tasks in the list.");
        showLine();
    }
}

