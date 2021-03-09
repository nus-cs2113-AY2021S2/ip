package main;

import main.commands.CommandResult;
import main.commands.FindTaskCommand;
import main.commands.ListCommand;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * Ui class handles the formatting and displaying of messages to the user.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class Ui {
    /**
     * A decorative prefix for the beginning of the line
     */
    public static final String LINE_PREFIX = "  ";

    /**
     * Logo for the application
     */
    public static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    /**
     * Format of welcome and goodbye messages
     */
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke - your personal task manager";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again";
    /**
     * Incorrect Command message
     */
    public static final String MESSAGE_INCORRECT_COMMAND = "Incorrect command! Please try again! \n%1$s";
    /**
     * Invalid Command Message
     */
    public static final String MESSAGE_INVALID_COMMAND = "Invalid command! Please try again!";
    /**
     * Invalid Command Message
     */
    public static final String MESSAGE_LOAD_DATA_ERROR =
            "Previously saved data not found or corrupted. Please start a new task list! ";
    /**
     * Index invalid message.
     */
    public static final String MESSAGE_INVALID_TASK_INDEX = "The task index you have provided is invalid";
    /**
     * A platform independent line separator.
     */
    protected static final String LS = System.lineSeparator();
    protected static final String LINE_DIVIDER =
            "\t__________________________________________________________________________";
    private static PrintStream out;
    private final Scanner in;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        Ui.out = out;
    }

    /**
     * Shows message(s) to the user
     */
    public static void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public static void showResultToUser(CommandResult result) {
        final TaskList taskList = result.getTaskList();
        if (Objects.equals(result.commandWord, ListCommand.COMMAND_WORD)) {
            if (taskList == null || taskList.size() == 0) {
                showToUser("\tTask list is empty. Add some tasks! ");
                showToUser(LINE_DIVIDER);
                return;
            }
        } else if (Objects.equals(result.commandWord, FindTaskCommand.COMMAND_WORD)) {
            if (result.matchedTaskList == null || result.matchedTaskList.size() == 0) {
                showToUser("\t No task found with given keyword! ");
            } else {
                showToUser(result.matchedTaskList.taskListAsString());
            }
            showToUser(LINE_DIVIDER);
            return;
        }
        showToUser(result.feedbackToUser);
        showToUser(LINE_DIVIDER);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Echos the command back to the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();

        showToUser("[You have entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcomeMessage() {
        showToUser(
                LOGO,
                LINE_DIVIDER,
                MESSAGE_WELCOME,
                LINE_DIVIDER);
    }

    /**
     * Generates and prints goodbye message upon completion of the application
     */
    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE, LINE_DIVIDER, LINE_DIVIDER);
    }

    /**
     * Shows the Line Divider to the User.
     */
    public void showLine() {
        System.out.println(LINE_DIVIDER);
    }
}
