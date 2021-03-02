package duke;

import static duke.common.Constants.BORDER;
import static duke.common.Constants.NEWLINE;

import java.util.Scanner;

/**
 * Text user interface of the application.
 */
public class Ui {

    // Display messages
    public static final String LOGO =
            "                                      ,::::," + "\n"
                    + "                          ,,,,:::::::':::::::" + "\n"
                    + "        ,::::.     ..:::~~           \\::::::" + "\n"
                    + "       ::::::::::~''      ':       __     ':." + "\n"
                    + "       ::::::/        __    .o.. : u ::     ':." + "\n"
                    + "         :::,       :: u :.' '. ' ':::'     '::    ,, .::,," + "\n"
                    + "          `::       ':::' /.  : .\\         .::   ::::::::::" + "\n"
                    + "          ::.               '':'            ::'  :::,'''.:::'" + "\n"
                    + "          `::                  :          ,::'  ,:::',,,':::'" + "\n"
                    + "           `::,                 .     ..:::.   ::::::::::::'" + "\n"
                    + "              ':::::,,,....     :..::::~    ``:::::::::::'" + "\n"
                    + "              :::::::::::::::::'~   .          ''::::::'" + "\n"
                    + "              ::::::::::::::::::.      .         `::::'" + "\n"
                    + "              ::::::::::::::::::::,     .          ::" + "\n"
                    + "               ::::::::::::::::::::      :         ::" + "\n"
                    + "                  ::::::::::::::::'      .       .::'" + "\n"
                    + "                  :: '::::::::::::'       .     .:':" + "\n"
                    + "                   ::    ~~::::''        . ,,,::::,,:::::" + "\n"
                    + "                   :::,,,,,,,,,,,....:::::::::::::::::::::" + "\n"
                    + "                  :::::::::::::::::   :::::::::::::::::::::" + "\n"
                    + "                 .:::::::::::::::::,,  :::::::::::::::::''" + "\n"
                    + "                .::::::::::::::::::::,   :::::::::::::'" + "\n"
                    + "                `:::::::::::::::::::::,'" + "\n";
    public static final String GREET_MESSAGE = BORDER
            + LOGO + NEWLINE
            + "Hello, I'm Panda!" + NEWLINE
            + "What would you like to do today?" + NEWLINE
            + "Tip: use \"help\" to view all valid commands" + NEWLINE
            + BORDER + NEWLINE;
    public static final String GOODBYE_MESSAGE = BORDER
            + "Alright! Goodbye :)" + NEWLINE
            + BORDER;

    // Help page
    public static final String HELP_PAGE = BORDER
            + "PANDA'S HELP PAGE" + NEWLINE
            + "Here are all the valid commands:" + NEWLINE + NEWLINE
            + "\thelp" + NEWLINE
            + "\t - displays all valid commands" + NEWLINE + NEWLINE
            + "\ttodo <description>" + NEWLINE
            + "\t- adds given task to the list" + NEWLINE + NEWLINE
            + "\tevent <description> /at <timing>" + NEWLINE
            + "\t- adds given task with details to the list" + NEWLINE + NEWLINE
            + "\tdeadline <description> /by <yyyy-mm-dd> <HH:mm>" + NEWLINE
            + "\t- adds given task with date and time to the list" + NEWLINE
            + "\t- example: add task with deadline \"2 Dec 2021, 6PM\":" + NEWLINE
            + "\t  deadline final essay /by 2021-12-02 18:00" + NEWLINE + NEWLINE
            + "\tlist" + NEWLINE
            + "\t- displays all tasks in the list" + NEWLINE + NEWLINE
            + "\tfilter <yyyy-mm-dd>" + NEWLINE
            + "\t- displays any deadlines in the list that match the given date" + NEWLINE + NEWLINE
            + "\tdone <index>" + NEWLINE
            + "\t- marks existing task matching the given index" + NEWLINE
            + "\t  as completed in the list" + NEWLINE  + NEWLINE
            + "\tfind <keyword>" + NEWLINE
            + "\t- displays a list of any tasks that match the given keyword" + NEWLINE + NEWLINE
            + "\tdelete <index>" + NEWLINE
            + "\t- deletes existing task matching the given index from the list" + NEWLINE + NEWLINE
            +"\tbye" + NEWLINE
            + "\t- stops the task manager" + NEWLINE + NEWLINE
            + "<> indicates an input field." + NEWLINE
            + BORDER + NEWLINE;

    // Opening statements for printList method
    public static final String PRINT_NEW_TASK_STATEMENT
            = BORDER + "New task added:" + NEWLINE;
    public static final String PRINT_DONE_TASK_STATEMENT
            = BORDER + "Yay! This task is now done:" + NEWLINE;
    public static final String PRINT_DELETED_TASK_STATEMENT
            = BORDER + "This task has been removed:" + NEWLINE;
    public static final String PRINT_FULL_LIST_STATEMENT
            = BORDER + "Here are the tasks in your list:" + NEWLINE;
    public static final String PRINT_TARGET_LIST_STATEMENT
            = BORDER + "Tasks matching this keyword:" + NEWLINE;

    // Invalid command error messages
    public static final String DEFAULT_INVALID_MESSAGE
            = "Sorry, I don't quite understand :( Could you try again?";
    public static final String INVALID_OR_MISSING_FIELDS_MESSAGE
            = "Hmm... Panda thinks you either entered an invalid command, "
            + "or missed some fields.";
    public static final String MISSING_FIELDS_MESSAGE
            = "Panda thinks you missed some fields! Try again?";
    public static final String INVALID_INDEX_MESSAGE
            = "Squeal! Second field must be a number.";
    public static final String OUTSIDE_RANGE_INDEX_MESSAGE
            = "Squeal? There is no task in the list with index ";
    public static final String INVALID_TASK_MESSAGE
            = "Squeal... Are you sure that is a task?";
    public static final String FOUND_NO_RESULTS_MESSAGE
            = "Panda can't find tasks in your list with that keyword...";
    public static final String INVALID_DATE_FORMAT_MESSAGE
            = "Squeal, date format is invalid!" + NEWLINE
            + "Use \"help\" for more info.";
    public static final String FILTERED_NO_RESULTS_MESSAGE
            = "Hmm, Panda couldn't find tasks in your list with that date...";

    // Other error messages
    public static final String LOADING_ERROR_MESSAGE
            = "Warning: failed to create a new directory or file." + NEWLINE
            + "Task list generated in this run will not be saved.";
    public static final String LOAD_TASK_ERROR_MESSAGE
            = "Warning: failed to load some tasks." + NEWLINE
            + "Task list may be incomplete.";
    public static final String SAVING_ERROR_MESSAGE
            = "Failed to save to disk.";


    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void printGreeting() {
        System.out.print(GREET_MESSAGE);
    }

    public void printGoodbye() {
        System.out.print(GOODBYE_MESSAGE);
    }

    /**
     * Displays a pre-defined explanation for the error if {@code errorMessage} is
     * not null, and a default message if it is null.
     * @param errorMessage name of a specific explanation
     */
    public void printErrorMessage(String errorMessage) {
        String closeString = NEWLINE + BORDER + NEWLINE;
        if (errorMessage == null) {
            System.out.print(BORDER + DEFAULT_INVALID_MESSAGE + closeString);
            return;
        }
        System.out.print(BORDER + errorMessage + closeString);
    }

    public void printHelpPage() {
        System.out.print(HELP_PAGE);
    }

    /**
     * Displays the most-recently modified task in {@code TaskList},
     * by way of confirmation to the user.
     */
    public void echo(TaskList tasks, int taskIndex, String openingStatement) {
        System.out.print(openingStatement);
        System.out.print("\t");
        tasks.getTaskAtIndex(taskIndex).printTask();
        System.out.print(NEWLINE);
    }

    /**
     * Displays all tasks in a {@code TaskList} in their full form, indexed.
     * @param tasks list of tasks
     * @param openingStatement message giving context on the type of {@code TaskList}
     *                         being displayed (filtered or main list)
     */
    public void printList(TaskList tasks, String openingStatement) {
        System.out.print(openingStatement);
        for (int i=0; i<tasks.getTasksCount(); i++) {
            System.out.print("\t" + (i + 1) + ". ");
            tasks.getTaskAtIndex(i).printTask();
            System.out.print(NEWLINE);
        }
        System.out.print(BORDER + NEWLINE);
    }

    /**
     * Displays a filtered list of {@code Deadline} tasks in their condensed form
     * (only deadline item and time).
     */
    public void printDeadlinesWithTargetDate(TaskList tasks) {
        System.out.print(BORDER + "Deadlines on this day:" + NEWLINE);
        for (int i=0; i<tasks.getTasksCount(); i++) {
            System.out.print("\t");
            tasks.getTaskAtIndex(i).printCondensedTask();
            System.out.print(NEWLINE);
        }
        System.out.print(BORDER + NEWLINE);
    }

    /**
     * Shows the number of items in {@code TaskList} when a {@code DoneCommand} or
     * {@code DeleteCommand} is executed, so that the user can keep track of
     * their tasks more easily.
     */
    public void printNumberOfTasks(int tasksCount) {
        System.out.print("There ");
        System.out.print(tasksCount > 1 ? "are " : "is ");
        System.out.print(tasksCount);
        System.out.print(tasksCount > 1 ? " tasks" : " task");
        System.out.print(" in your list." + NEWLINE);
        System.out.print(BORDER + NEWLINE);
    }

    public void printBorder() {
        System.out.print(BORDER + NEWLINE);
    }
}
