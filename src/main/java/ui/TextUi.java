package ui;

import tasklist.TaskList;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents all interactions with the user.
 */
public class TextUi {

    private static final String LINE_BREAK = "----------------------------------------------------";

    private static final Scanner input = new Scanner(System.in);

    /**
     * Shows welcome message to user.
     */
    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE_BREAK);
        System.out.println(logo);
        System.out.println("Aloha! I'm Duke");
        System.out.println("What would you like to do today?");
        System.out.println("Enter 'Bye' when done.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Shows goodbye message to user.
     */
    public static void showGoodbyeMessage() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye! Hope you have a wonderful day.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Gets user's input command.
     * Ignores input in the form of whitespaces only or newline.
     * @return String action.
     */
    public static String getUserCommand() {
        String action = null;
        if(!input.hasNext() || (action = input.nextLine()).isEmpty()) System.exit(0);
        return action;
    }

    /**
     * Shows task added message to user.
     */
    public static void taskAddedMessage(Task t) {
        System.out.println(LINE_BREAK);
        System.out.println("Got it. I've added this task:");
        t.toString();
        System.out.println("Now you have " + TaskList.allTasks.size() + " tasks in the list.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Shows entire task list to user.
     */
    public static void showTaskList() {
        int taskNum;
        System.out.println(LINE_BREAK);
        System.out.println("Your current tasks: ");
        for (int i=0; i < TaskList.allTasks.size(); i++) {
            if (TaskList.allTasks.get(i) != null) {
                taskNum = i + 1;
                System.out.println(taskNum + ". " + TaskList.allTasks.get(i).toString());
            }
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Shows user the task he has marked as done.
     * @param taskNum task number of the task the user has marked as done.
     */
    public static void taskDoneMessage(int taskNum) {
        System.out.println("You've completed " + TaskList.allTasks.get(taskNum).getTask() + "!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Shows user the task he has deleted.
     * @param taskNum task number of the task the user has deleted.
     */
    public static void taskDeleteMessage(int taskNum) {
        System.out.println("Noted. I have removed this task:");
        System.out.println(TaskList.allTasks.get(taskNum).toString());
        System.out.println("Now you have " + (TaskList.allTasks.size() - 1) + " tasks in the list.");
    }

    /**
     * Informs user that his input command is invalid.
     */
    public static void showInvalidInputMessage() {
        System.out.println(LINE_BREAK);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(LINE_BREAK);
    }

    /**
     * Shows user tasks in his task list that match his keyword.
     * @param matchList List of matched tasks.
     */
    public static void showMatchedTasks(ArrayList<Task> matchList) {
        System.out.println(LINE_BREAK);
        for (int i=0; i < matchList.size(); i++) {
            int taskNum = i + 1;
            System.out.println(taskNum + ". " + matchList.get(i).toString());
        }
        System.out.println(LINE_BREAK);
    }

}
