package ui;

import task.Task;

import java.util.ArrayList;

/**
 * Represents a class that handles the printing of output to the console.
 */
public class Printer {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints the message when Duke is started up.
     */
    public static void printStartUpMessage() {
        System.out.println(LOGO);
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the exit message when the bye command is issued and Duke is exiting.
     */
    public static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the tasks in the task list.
     *
     * @param tasks The task list.
     */
    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to tell the user the task has been added successfully to the task list.
     *
     * @param taskToAdd The task that to be added.
     * @param taskCount The number of tasks in the task list.
     */
    public static void addTaskSuccessfulMessage(Task taskToAdd, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskToAdd);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to tell the user the task has been marked as done successfully in the task list.
     *
     * @param taskToMarkDone The task to be marked as done.
     */
    public static void taskMarkedAsDoneMessage(Task taskToMarkDone) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToMarkDone);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to tell the user the task has been deleted successfully from the task list.
     *
     * @param taskToDelete The task to be deleted from the task list.
     * @param taskCount    The number of tasks in the task list.
     */
    public static void deleteTaskSuccessfulMessage(Task taskToDelete, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task.");
        System.out.println(taskToDelete);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to tell the user the index they have entered is out of bounds of the task list size.
     */
    public static void taskIndexOutOfBoundsMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("The index you entered is out of bounds, please enter a valid index.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to tell the user that the find command must contain a keyword.
     */
    public static void keyWordCannotBeEmptyMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Keyword for find command cannot be empty");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to tell the user that no matching tasks with the given keyword are found in the task list.
     *
     * @param keyword The keyword to be matched.
     */
    public static void printNoMatchingTasks(String keyword) {
        System.out.println("____________________________________________________________");
        System.out.println("No tasks matching with " + keyword + " was found in your task list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the matching tasks in the task list that match the keyword.
     *
     * @param matchingTasks A list containing the tasks in the task list that match with they keyword.
     */
    public static void printMatchingTaskList(ArrayList<Task> matchingTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, matchingTasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to tell the user that the delete command is formatted incorrectly.
     */
    public static void invalidDeleteFormatMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Invalid format! Format the delete command like this \"delete [index]\"");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to tell the user that the done command is formatted incorrectly.
     */
    public static void invalidDoneFormatMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Invalid format! Format the done command like this \"done [index]\"");
        System.out.println("____________________________________________________________");
    }
}
