package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * The UI class contains methods that deal with the user
 * UI has methods to read user input and also print output
 */
public class Ui {
    private final Scanner in;
    private static final String exceptionGreeting = "\ud83d\ude16 OOPS!!! ";

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Prints the Duke logo and welcomes the user
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints goodbye message when the user is exiting the program
     */
    public void showExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal line made of dashes, intending to separate user input and program output
     */
    public void printHorizontalLine() {
        System.out.println("\t---------------------------------------------------------------------");
    }

    /**
     * Prints out all existing tasks in the TaskList object
     *
     * @param tasks TaskList object
     */
    public void printTasksList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        System.out.print(tasks);
    }

    /**
     * Returns String of next line of user's input
     *
     * @return String of user's input
     */
    public String readCommand() {
        String fullCommand = in.nextLine();
        return fullCommand;
    }

    /**
     * Prints the error message prepended with an error greeting
     *
     * @param message String obtained from exception
     */
    public void showError(String message) {
        System.out.println("\t" + exceptionGreeting + message);
    }

    /**
     * Prints out message to confirm to the user that the task has been marked as done
     *
     * @param tasks TaskList object
     * @param index index that the task that was marked as done corresponds to in tasks
     */
    public void printDoneTask(TaskList tasks, int index) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.print("\t  ");
        System.out.println(tasks.getTask(index));
    }

    /**
     * Prints out message to confirm to the user that the task has been removed/deleted
     *
     * @param t Task object that user has removed
     * @param taskCounter Total number of tasks remaining
     */
    public void printRemoveTask(Task t, int taskCounter) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.print("\t  ");
        System.out.println(t);
        if (taskCounter == 1) {
            System.out.println("\tNow you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskCounter + " tasks in the list.");
        }
    }

    /**
     * Prints out message to confirm to the user that the task has been added
     * @param t Task object that user has added
     * @param taskCounter Total number of tasks after adding new task
     */
    public void printNewTask(Task t, int taskCounter) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + t);
        if (taskCounter == 1) {
            System.out.println("\tNow you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskCounter + " tasks in the list.");
        }
    }

    /**
     * Prints out error message to indicate user has tried to add new task but left description empty
     *
     * @param type Type of task that the user tried to add
     */
    public void printEmptyDescription(String type) {
        System.out.println("\t" + exceptionGreeting + "The description of a " + type + " cannot be empty.");
    }

    /**
     * Prints out error message prepended with error greeting to indicate user has specified invalid task number
     */
    public void printInvalidTask() {
        System.out.println("\t" + exceptionGreeting + "That task number does not exist.");
    }

    /**
     * Prints out error message to indicate an error while creating a directory in which the data file would be stored
     */
    public void printDirectoryError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error while creating a directory :-(");
    }

    /**
     * Prints out error message to indicate an error while creating the data file
     */
    public void printFileError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error while creating a data file :-(");
    }

    /**
     * Prints out error message to indicate an error while loading data from the data file
     */
    public void printLoadError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error loading your data :-(");
    }

    /**
     * Prints out error message to indicate an error while saving user's data to the data file
     */
    public void printSaveError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error saving your data :-(");
    }
}
