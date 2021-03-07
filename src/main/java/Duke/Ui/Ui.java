package Duke.Ui;

import Duke.Constant.SpaceAndLine;
import Duke.TaskList.TaskList;

import java.util.Scanner;

/**
 * Ui receives inputs from the user and subsequently produces relevant outputs to him / her.
 */
public class Ui {
    private final Scanner userInput;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    /**
     * Prints a message received.
     *
     * @param message some message which needs to printed by a ui
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message about how many tasks there are.
     *
     * @param numberOfTasks number of tasks
     */
    public static void printNumberOfTasksMessage(int numberOfTasks) {
        if (numberOfTasks == 0 || numberOfTasks == 1) {
            System.out.println(SpaceAndLine.SHORT_SPACE + "Now you have " + numberOfTasks + " task in the list.");
        } else {
            System.out.println(SpaceAndLine.SHORT_SPACE + "Now you have " + numberOfTasks + " tasks in the list.");
        }
    }

    /**
     * Reads user input line by line.
     *
     * @return the string of the line read
     */
    public String readCommand() {
        return userInput.nextLine();
    }

    /**
     * Prints all the tasks.
     */
    public void printTasks(TaskList tasks) {
        tasks.displayList();
    }

    /**
     * Prints all the matched tasks after a find command.
     */
    public void printMatchedTasks(TaskList matchedTasks) {
        matchedTasks.DisplayMatchedTasks();
    }

    /**
     * Prints all the tasks with the same date specified in a find command.
     */
    public void printSameDateTasks(TaskList sameDateTasks) {
        sameDateTasks.DisplaySameDateTasks();
    }

    /**
     * Prints a relevant error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
