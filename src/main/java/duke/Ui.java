package duke;

import java.io.IOException;

/**
 * A class containing the methods necessary to display Ui messages to user
 */
public class Ui {


    /**
     * Prints Ui message when user calls "List"
     */
    static void listBeginMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints Ui message when user calls "Find"
     */
    static void findTaskMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints Ui message when user deletes a task
     * Note that the first line cannot be split into two,
     *  otherwise a Ui bug would be introduced into the program.
     * Exception is thrown if task index is invalid.
     */
    static void deletedTaskMessage(int deletedTaskIndex) {
        System.out.println("Noted. I've removed this task: \n" + TaskList.tasks.get(deletedTaskIndex));
        System.out.println("Now you have " + (TaskList.maxTaskIndex - 1) + " tasks in the list." + "\n");
    }

    /**
     * Prints Ui message when user marks a Task as Done
     */
    static void completedTaskMessage(int completedTaskIndex) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + TaskList.tasks.get(completedTaskIndex).getStatusIcon() + "] " + TaskList.tasks.get(completedTaskIndex).getDescription() + "\n");
    }

    /**
     * Prints Ui message when user enters Duke
     */
    static void welcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints Ui message when user exits Duke
     */
    static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints Ui message after user adds a new Task
     */
    static void confirmNewTaskMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(TaskList.tasks.get(TaskList.maxTaskIndex));

        int numTasks = getNumTasks();
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Prints Exception message if IO Error
     */
    static void showLoadingError(IOException e) {
        System.out.println("Can't load from file: " + e.getMessage());
    }

    /**
     * Prints Exception message if User inputs Task Index that does not exist in the List
     */
    static void showTaskIndexNotExistsError() {
        System.out.println("OOPS!!! You need to add valid Task Index to be done or deleted!!");
    }

    /**
     * Prints Exception message if User inputs non-integer in Task Index field
     */
    static void showInvalidIntegerTaskIndexError() {
        System.out.println("OOPS!!! I don't recognise the number to process Task Index to be done or deleted!!");
    }

    /**
     * Prints Exception message if User does not input valid time in Deadline with keyword '/by'.
     */
    static void showInvalidDeadlineTimeError(InvalidDeadlineTimeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints Exception message if User does not input valid time in Event with keyword '/at'.
     */
    static void showInvalidEventTimeError(InvalidEventTimeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints Exception message if User did not input time according to the format.
     */
    static void showNoTimeAddedError() {
        System.out.println("OOPS!!! You need to add time for new Event or Deadline with '/at' or '/by'!!");
    }

    /**
     * Prints Exception message if User did not even input Task description at all.
     */
    static void showEmptyInputError(EmptyInputException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints Exception message if User did not input valid Command keyword
     */
    static void showInvalidCommandError(InvalidCommandException e) {
        System.out.println(e.getMessage());
    }


    private static int getNumTasks() {
        return TaskList.maxTaskIndex + 1;
    }
}
