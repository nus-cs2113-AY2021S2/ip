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
    public static final String HELP_PAGE = BORDER
            + "HELP PAGE" + NEWLINE
            + "This is the list of all valid commands:" + NEWLINE + NEWLINE
            + "\thelp" + NEWLINE
            + "\t - displays all valid commands" + NEWLINE + NEWLINE
            +"\tbye" + NEWLINE
            + "\t- stops the task manager" + NEWLINE + NEWLINE
            + "\tlist" + NEWLINE
            + "\t- displays all tasks in the list" + NEWLINE + NEWLINE
            + "\ttodo     | <task>" + NEWLINE
            + "\t- adds specified task to the list" + NEWLINE + NEWLINE
            + "\tdeadline | <task>  | /by | <deadline>" + NEWLINE
            + "\t- adds specified task and deadline and to the list" + NEWLINE + NEWLINE
            + "\tevent    | <task>  | /at | <timing>" + NEWLINE
            + "\t- adds specified task and timing to the list" + NEWLINE + NEWLINE
            + "\tdone     | <index>" + NEWLINE
            + "\t- marks existing task matching the specified index"
            + "as completed in the list" + NEWLINE  + NEWLINE
            + "<> indicates an input field and | is a field separator." + NEWLINE
            + BORDER + NEWLINE;


    // Invalid command error messages
    public static final String DEFAULT_INVALID_MESSAGE
            = "I'm sorry, I don't quite understand :( Could you try again?";
    public static final String MISSING_FIELDS_MESSAGE
            = "I think you missed some fields! Try again?";
    public static final String INVALID_INDEX_MESSAGE
            = "Squeal! Second field must be a number.";
    public static final String OUTSIDE_RANGE_INDEX_MESSAGE
            = "Squeal? There is no task in the list with index ";
    public static final String INVALID_TASK_MESSAGE
            = "Squeal... Are you sure that is a task?";


    // Other error messages
    public static final String LOADING_ERROR_MESSAGE
            = "Failed to load from disk, creating a new file!";
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
     * Displays a newly-added task in the {@code TaskList} when a {@code TaskCommand},
     * {@code DeadlineCommand} or {@code EventCommand} is executed.
     */
    public void echo(TaskList tasks) {
        System.out.print(BORDER);
        System.out.print("New task added: " + NEWLINE);
        System.out.print("\t");
        tasks.getTaskAtIndex(tasks.getTasksCount()-1).printTask();
        printNumberOfTasks(tasks.getTasksCount());
    }

    public void printNumberOfTasks(int tasksCount) {
        System.out.print(NEWLINE + "There ");
        System.out.print(tasksCount > 1 ? "are " : "is ");
        System.out.print(tasksCount);
        System.out.print(tasksCount > 1 ? " tasks" : " task");
        System.out.print(" in your list." + NEWLINE);
        System.out.print(BORDER + NEWLINE);
    }

    /**
     * Displays the entire {@code TaskList}.
     */
    public void printList(TaskList tasks) {
        System.out.print(BORDER);
        System.out.print("Here are the tasks in your list:" + NEWLINE);
        for (int i=0; i<tasks.getTasksCount(); i++) {
            System.out.print("\t" + (i + 1) + ". ");
            tasks.getTaskAtIndex(i).printTask();
            System.out.print(NEWLINE);
        }
        System.out.print(BORDER + NEWLINE);
    }
}
