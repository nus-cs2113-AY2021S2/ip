package duke.ui;

import duke.Command;
import duke.list.TaskList;
import duke.parser.Parser;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui handles the input and output of the Duke program.
 */
public class TextUi {
    private final Scanner in;

    /**
     * Initialises the input of the Duke program.
     */
    public TextUi() {
        in = initialiseInput();
    }

    private Scanner initialiseInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    /**
     * Prints the starting message of the Duke program.
     */
    public void printInitialMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Scans for the next line of input from user.
     *
     * @return line of input String
     */
    public String scanInput() {
        return in.nextLine();
    }

    /**
     * Prints reaction based on the command value.
     *
     * @param taskList        ArrayList of tasks
     * @param command         Command of user
     * @param userCommandText full String of user input
     */
    public void printReaction(TaskList taskList, Command command, String userCommandText) {
        switch (command) {
        case BYE:
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case LIST:
            System.out.println(taskList);
            break;
        case DONE:
            int taskNum = Parser.getTaskNum(userCommandText, Command.DONE);
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                    taskList.getTask(taskNum - 1));
            break;
        case DELETE:
            taskNum = Parser.getTaskNum(userCommandText, Command.DELETE);
            System.out.println("Noted. I've removed this task:" + System.lineSeparator() +
                    taskList.getDeletedTask(taskNum - 1));
            break;
        case FIND:
            ArrayList<Task> foundTasks = taskList.findTask(userCommandText.replaceFirst("find ", ""));
            if (foundTasks.size() == 0) {
                System.out.println("No tasks found.");
                break;
            }
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : foundTasks) {
                System.out.println(task);
            }
            break;
        case DONE_ERROR:
            System.out.println("Done command needs task number within the range of the list!");
            break;
        case DELETE_ERROR:
            System.out.println("Delete command needs task number within the range of the list!");
            break;
        case FIND_ERROR:
            System.out.println("Find command needs a keyword!");
            break;
        case ADD_ERROR:
            System.out.println("The description cannot be empty!");
            break;
        case ERROR:
            System.out.println("Wrong command entered!: " + userCommandText);
            break;
        default:
            System.out.println("Added: " + taskList.getLastTask());
        }
    }

    /**
     * Checks if user wants to exit the Duke program
     *
     * @param command user command
     * @return true if user wants to exit. Else, false.
     */
    public boolean isExit(Command command) {
        return command == Command.BYE;
    }

    /**
     * Error message when unable to load the file.
     */
    public void printLoadFileError() {
        System.out.println("Unable to load records from a file. Continuing Duke with an empty record.");
    }

    /**
     * Error message when unable to save into the file
     */
    public void printSaveFileError() {
        System.out.println("Unable to save records into file. Duke exiting.");
    }

    /**
     * Error message when the file is corrupted
     */
    public void corruptedFileError() {
        System.out.println("Parts of file were corrupted with wrong commands. Loaded only the uncorrupted commands.");
    }
}
