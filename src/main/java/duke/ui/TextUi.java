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
    
    // METHODS

    public void printTaskAdded(Task task) {
        System.out.println("Added to list: ");
        System.out.print(INDENT);
        task.printTask();
        printNumTasksLeft();
        System.out.println();
    }

    public void printTaskDeleted(int index) {
        System.out.println("Task " + (index + 1) + " has been deleted:");
        System.out.print(INDENT);
        tasks.get(index).printTask();
        System.out.println("Tasks remaining: " + (tasks.getCount() - 1) + "\n");
    }

    public void printTaskAsList(int numbering, Task task) {
        System.out.print(numbering + ". ");
        task.printTask();
    }

    private static void printNumTasksLeft() {
        String output = Integer.toString(tasks.getCount());
        output += (tasks.getCount() == 1) ? " task" : " tasks";
        output += " in the list";

        System.out.println(output);
    }

    public void printSearchResultsHeader(String keyword) {
        System.out.println("SEARCH RESULTS FOR \"" + keyword + "\":");
    }

    public void printUnknownCommandWarning(String input) {
        System.out.println("No idea what \"" + input + "\" means!");
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }

    public void printInvalidInputWarning(String input) {
        System.out.println("Wrong format: \"" + input + "\" !");
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }

    public void printNoTaskWarning() {
        System.out.println("You don't have any tasks ! Enter a task");
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }

    public void printInvalidIndexWarning(int jobNumber) {
        System.out.println(jobNumber <= 0 ? JOB_NUMBER_TOO_SMALL : JOB_NUMBER_TOO_BIG);
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }

    public void printListFullWarning() {
        System.out.println(LIST_FULL_WARNING);
        System.out.println(PROMPT_ENTER_BYE_TO_EXIT);
    }

    public void printNoMatchWarning(String keyword) {
        System.out.println(NO_MATCH_WARNING);
        System.out.println(INDENT + keyword + '\n');
    }

    public void printDataErrorWarning(int line) {
        System.out.println("Error forming task. Check formatting at line " + line + " in data file!");
        System.out.println();
    }

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

    public void printHello() {
        System.out.println(STRAIGHT_LINE);
        System.out.print(ALLO_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }

    public void printBye() {
        System.out.println(STRAIGHT_LINE);
        System.out.print(BYE_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }

    public void printInvalidDateFormatWarning() {
        System.out.println(INVALID_DATE_FORMAT_WARNING);
        System.out.println(ENTER_HELP_FOR_LIST_OF_COMMANDS);
    }
}
