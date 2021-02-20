package duke.ui;

import duke.tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static duke.Duke.taskList;
import static duke.constants.UiStrings.*;


public class TextUi {

    private final Scanner in;
    private final PrintStream out;
    
    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printTaskAdded(Task task) {
        System.out.println("Added to list: ");
        task.printTask();
        printNumTasksLeft();
        System.out.println();
    }

    public void printTaskDeleted(int index) {
        System.out.println("Task " + (index + 1) + " has been deleted:");
        System.out.print(INDENT);
        taskList.get(index).printTask();
        System.out.println("Tasks remaining: " + (taskList.size() - 1) + "\n");
    }

    private static void printNumTasksLeft() {
        String output = Integer.toString(taskList.size());
        output += (taskList.size() == 1) ? " task" : " tasks";
        output += " in the list";

        System.out.println(output);
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

    private static void printListFullWarning() {
        System.out.println(LIST_FULL_WARNING);
        System.out.println(PROMPT_ENTER_BYE_TO_EXIT);
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
        System.out.println(DEADLINE_COMMAND_FORMAT + '\n');
        System.out.println(EVENT_COMMAND_FORMAT + '\n');
        System.out.println(LIST_COMMAND_FORMAT + '\n');
        System.out.println(DONE_COMMAND_FORMAT + '\n');
        System.out.println(DELETE_COMMAND_FORMAT + '\n');
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
    
}
