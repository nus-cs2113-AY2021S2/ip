package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the object that handles interactions with user
 * Mainly prints statements and requests for inputs from the user
 */
public class Ui {
    private static final String lineSpacing = "\t----------------------------------";
    private String line;
    private Scanner in;

    /**
     * Constructor of this class. Initializes a scanner to read user inputs
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Reads the user commands
     * @return The input given by the user as a string
     */
    public String readCommand() {
        line = in.nextLine();
        return line;
    }

    public void showError(String errMessage) {
        System.out.println(errMessage);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greetings = "\tHello! I'm Duke\n" + "\tWhat can I do for you?\n\n";
        System.out.println(lineSpacing);
        System.out.println(greetings);
        System.out.println(lineSpacing);
    }

    public void showGoodbye() {
        String exitStatements = "\tBye. Hope to see you again soon!\n";
        System.out.println(lineSpacing);
        System.out.println(exitStatements);
        System.out.println(lineSpacing);
    }

    public void showLine() {
        System.out.println(lineSpacing);
    }

    public void showLoadingError() {
        System.out.println("Unable to load from file. Will initialize a new todo list for you!");
    }

    public void showAddedTasks(Task task, Integer tasksSize) {
        System.out.println("\tGot it. I've added this task:");
        System.out.print("\t");
        task.printTask();
        System.out.println("\tNow you have " + tasksSize + " tasks in the list");
    }

    public void showDisplayTasks(TaskList taskList) {
        System.out.println("\tHere are the tasks in your list");
        Integer counter = 1;
        for (Task task: taskList.getTaskList()) {
            System.out.print("\t" + counter + ".");
            counter += 1;
            task.printTask();
        }
    }

    public void showDeletedTasks(ArrayList<Task> deletedTaskList, Integer tasksSize) {
        System.out.println("\tNoted. I've removed this task:");
        for (Task task: deletedTaskList) {
            System.out.print("\t");
            task.printTask();
        }
        System.out.println("\tNow you have " + tasksSize + " tasks in the list");
    }

    public void showUpdatedTasks(ArrayList<Task> updatedTaskList) {
        for (Task task: updatedTaskList) {
            System.out.print("\t");
            task.printTask();
        }
    }

    public void showFoundTasks(ArrayList<Task> foundTasks) {
        Integer counter = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task: foundTasks) {
            System.out.print("\t" + counter + ".");
            task.printTask();
        }
    }
}
