package duke.ui;

import duke.tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static duke.Duke.tasks;
import static duke.constants.ProgramStrings.*;

/**
 * Text UI of the Duke application
 */
public class TextUi {

    protected final Scanner in;
    protected final PrintStream out;
    
    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays confirm message to user that a task has been added.
     * The added task is printed along with the number of tasks currently in the list.
     */
    public void printTaskAdded(Task task) {
        System.out.println(PROMPT_TASK_ADDED);
        System.out.print(INDENT);
        task.printTask();
        printNumTasksLeft();
        System.out.println();
    }

    /**
     * Displays confirm message to user that a task has been deleted.
     * The deleted task is printed along with the number of remaining tasks in the list.
     */
    public void printTaskDeleted(int index) {
        System.out.println("Task " + (index + 1) + " has been deleted:");
        System.out.print(INDENT);
        tasks.get(index).printTask();
        System.out.println("Tasks remaining: " + (tasks.getCount() - 1) + "\n");
    }

    /**
     * Prints the task object in the list format with numbering.
     */
    public void printTaskAsList(int numbering, Task task) {
        System.out.print(numbering + ". ");
        task.printTask();
    }

    /**
     * Prints the number of tasks currently in the task list.
     */
    private static void printNumTasksLeft() {
        String output = Integer.toString(tasks.getCount());
        // Check for plural or singular form
        output += (tasks.getCount() == 1) ? " task" : " tasks";
        output += " in the list";

        System.out.println(output);
    }

    /**
     * Displays header for listing search results.
     */
    public void printSearchResultsHeader(String keyword) {
        System.out.println("SEARCH RESULTS FOR \"" + keyword + "\":");
    }

    /**
     * Displays warning to user when entered input is not recognised by the program.
     * User's invalid input is echoed back to the user along with a prompt to utilise the {@code help} command.
     */
    public void printUnknownCommandWarning(String input) {
        System.out.println("No idea what \"" + input + "\" means!");
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }

    /**
     * Displays warning to user when entered input is recognised but it either does not follow the required format
     * or lacks required parameters.
     * User's invalid input is echoed back to the user along with a prompt to utilise the {@code help} command.
     */
    public void printInvalidInputWarning(String input) {
        System.out.println("Wrong format: \"" + input + "\" !");
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }

    /**
     * Displays warning to user when user attempts to view or manipulate tasks when task list is empty.
     * User is also prompted to utilise the {@code help} command.
     */
    public void printNoTaskWarning() {
        System.out.println("You don't have any tasks ! Enter a task");
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }

    /**
     * Displays warning to user when user enters an invalid index for certain commands.
     * User is shown a different warning depending on whether the entered index is too small or too large.
     */
    public void printInvalidIndexWarning(int jobNumber) {
        System.out.println(jobNumber <= 0 ? JOB_NUMBER_TOO_SMALL : JOB_NUMBER_TOO_BIG);
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }

    /**
     * Displays warning to user when list is full and user attempts to add new commands.
     */
    public void printListFullWarning() {
        System.out.println(LIST_FULL_WARNING);
        System.out.println(LIST_FULL_WARNING_2);
        System.out.println(PROMPT_ENTER_BYE_TO_EXIT);
    }

    /**
     * Displays prompt to user when {@code find} query returns no results.
     */
    public void printNoMatchWarning(String keyword) {
        System.out.println(NO_MATCH_WARNING);
        System.out.println(INDENT + keyword + '\n');
    }

    /**
     * Displays warning when encounter error reading from data file. Includes line where error occurred for easy
     * troubleshooting.
     */
    public void printDataErrorWarning(int line) {
        System.out.println("Error forming task. Check formatting at line " + line + " in data file!");
        System.out.println();
    }

    /**
     * Displays warning to user when entered date format is wrong.
     * User is prompted to utilise {@code help} command.
     */
    public void printInvalidDateFormatWarning() {
        System.out.println(INVALID_DATE_FORMAT_WARNING);
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }
    
    /**
     * Prints a manual for the user specifying every command available in Duke along with the required parameters and
     * formatting.
     */
    public void printHelp() {
        System.out.println(SHORT_LINE);
        System.out.println("COMMAND LIST:");
        System.out.println(SHORT_LINE);
        System.out.println(TODO_COMMAND_FORMAT + '\n');
        System.out.println(DEADLINE_COMMAND_FORMAT);
        System.out.println(DATE_FORMAT + '\n');
        System.out.println(EVENT_COMMAND_FORMAT);
        System.out.println(DATE_FORMAT + '\n');
        System.out.println(LIST_COMMAND_FORMAT + '\n');
        System.out.println(DONE_COMMAND_FORMAT + '\n');
        System.out.println(DELETE_COMMAND_FORMAT + '\n');
        System.out.println(FIND_COMMAND_FORMAT + '\n');
        System.out.println(PROMPT_ENTER_BYE_TO_EXIT);
        System.out.println(SHORT_LINE + '\n');
    }

    /**
     * Displays start message.
     */
    public void printHello() {
        System.out.println(STRAIGHT_LINE);
        System.out.print(ALLO_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Displays exit message.
     */
    public void printBye() {
        System.out.println(STRAIGHT_LINE);
        System.out.print(BYE_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }
}
