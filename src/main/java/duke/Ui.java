package duke;

import duke.task.Task;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Ui {

    /** Number of dashes for the separator */
    private static final int SEPARATOR_LENGTH = 80;

    public void showSeparator() {
        for(int i = 0; i < SEPARATOR_LENGTH; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public void showWelcome() {
        showSeparator();
        System.out.print("Greetings, fellow humans!\nI am CI.\nHow may I help you?\n");
        showSeparator();
    }

    public void showExit() {
        System.out.print("My cover's blown!\n");
    }

    public void showLoading() {
        System.out.print("Looking for existing data...\n");
    }

    public void showLoadingSuccess() {
        System.out.print("File loaded successfully.\n");
    }

    public void showLoadingError(File filePath) {
        System.out.print("No prior data found. New file created at " + filePath.getAbsolutePath()+ "\n");
    }

    public void showSavingSuccess(File filePath) {
        System.out.print("File saved successfully at " + filePath.getAbsolutePath() + "\n");
    }

    /**
     * Echoes the user input with random upper and lower case for mockery.
     * Shows the command the user may input for help after.
     *
     * @param line User input.
     */
    public void mockEcho(String line) {
        Random rd = new Random();
        for (int i = 0; i < line.length(); i++) {
            if (rd.nextBoolean()) {
                System.out.print(Character.toUpperCase(line.charAt(i)));
            } else {
                System.out.print(Character.toLowerCase(line.charAt(i)));
            }
        }
        System.out.print('\n');
        showHint();
    }

    public void showError(Exception e) {
        System.out.print("Something went wrong: " + e.getMessage() + "\n");
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showHint() {
        System.out.print("Use -h for list of available commands.\n");
    }

    public void showHelp() {
            System.out.print("I can remember your tasks for you!\n\n" +
                    "Available commands:\n" +
                    "\ttodo <description>\n" +
                    "\tdeadline <description> /by <date and time due>\n" +
                    "\tevent <description> /at <date and time occurring>\n" +
                    "\tlist\n" +
                    "\tdone <task index>\n" +
                    "\tdelete <task index>\n" +
                    "\tfind <keyword>\n" +
                    "\tbye\n"
                    );
    }

    public void showNoTasks() {
        System.out.print("You don't have any tasks currently!\n");
    }

    public void showInvalidNumber(String invalidNumber) {
        System.out.print(invalidNumber + " is not a valid number.\n");
    }

    public void showDeadlineByError() {
        System.out.print("Something went wrong. Please put the due date after /by.\n");
    }

    public void showEventAtError() {
        System.out.print("Something went wrong. Please put the event time after /at.\n");
    }

    public void showInvalidTaskIndex(int taskIndex) {
        System.out.print((taskIndex+1) + " is not a valid task index, please try again.\n");
    }

    public void showDoneEmptyError() {
        System.out.print("That is not a valid command. Please enter a number after the word.\n");
    }

    public void showListSize(int size) {
        System.out.print("There are " + size + " tasks in your list:\n");
    }

    public void printTask(int index, Task task) {
        System.out.print(index+ "." + task + '\n');
    }

    public void showNoTasksFound(String keyword) {
        System.out.print("There are no tasks with \"" + keyword +"\".\n");
    }

    public void showFindLoading(String keyword) {
        System.out.print("Finding tasks with \"" + keyword + "\" in their description...\n");
    }

    public void showDateTimeHint(String dateTimeFormat) {
        System.out.print("That is invalid. The date-time format should be: " + dateTimeFormat + "\n");
    }
}
