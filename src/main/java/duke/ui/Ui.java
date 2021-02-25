package duke.ui;

import duke.common.Messages;
import duke.task.Task;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user input and output for Duke
 */
public class Ui {
    private final String LINE = "\u2500".repeat(60);
    private final Scanner SCANNER = new Scanner(System.in);

    /**
     * Prints greeting message with lines.
     */
    public void printGreeting() {
        printLine();
        printText(Messages.INFO_GREETING);
        printLine();
    }

    /**
     * Prints bye message.
     */
    public void printBye() {
        System.out.println(Messages.INFO_BYE);
    }

    /**
     * Prints given text string.
     * @param text the text to be printed
     */
    public void printText(String text) {
        System.out.println(text);
    }

    /**
     * Prints task that was added by Duke.
     * @param task the added task
     */
    public void printAddedTask(Task task) {
        System.out.println(Messages.INFO_ADDED_TASK + task);
    }

    /**
     * Prints total tasks in the tasks list.
     * @param tasks the tasks list
     */
    public void printTotalTasks(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints error message of given DukeException.
     * @param error the exception for error message to be printed
     */
    public void printError(DukeException error) {
        System.out.println(error);
    }

    /**
     * Prints the deleted task line.
     * @param task the deleted task
     */
    public void printDeletedTask(Task task) {
        System.out.println(Messages.INFO_DELETED_TASK + task);
    }

    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Reads the user input by line.
     * @return string of the user's input
     */
    public String readUserInput() {
        String userInput;
        userInput = SCANNER.nextLine();
        return userInput;
    }
}
