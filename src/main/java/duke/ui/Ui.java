package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private static final String exceptionGreeting = "\ud83d\ude16 OOPS!!! ";

    public Ui() {
        in = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    public void showExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void printHorizontalLine() {
        System.out.println("\t---------------------------------------------------------------------");
    }

    public void printTasksList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        System.out.print(tasks);
    }

    public void printQueryList(String listOfTasks) {
        System.out.println("\tHere are the matching tasks in your list:");
        System.out.print(listOfTasks);
    }

    public String readCommand() {
        String fullCommand = in.nextLine();
        return fullCommand;
    }

    public void showError(String message) {
        System.out.println("\t" + exceptionGreeting + message);
    }

    public void printDoneTask(TaskList tasks, int index) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.print("\t  ");
        System.out.println(tasks.getTask(index));
    }

    public void printRemoveTask(Task t, int taskCounter) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.print("\t  ");
        System.out.println(t);
        if (taskCounter == 1) {
            System.out.println("\tNow you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskCounter + " tasks in the list.");
        }
    }

    public void printNewTask(Task t, int taskCounter) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + t);
        if (taskCounter == 1) {
            System.out.println("\tNow you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskCounter + " tasks in the list.");
        }
    }

    public void printEmptyDescription(String type) {
        System.out.println("\t" + exceptionGreeting + "The description of a " + type + " cannot be empty.");
    }

    public void printEmptyQuery() {
        System.out.println("\t" + exceptionGreeting + "The query cannot be empty.");
    }

    public void printInvalidTask() {
        System.out.println("\t" + exceptionGreeting + "That task number does not exist.");
    }

    public void printDirectoryError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error while creating a directory :-(");
    }

    public void printFileError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error while creating a data file :-(");
    }

    public void printLoadError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error loading your data :-(");
    }

    public void printSaveError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error saving your data :-(");
    }

    public void printDateError() {
        System.out.println("\t" + exceptionGreeting + "I've encountered an error parsing your date :-(");
    }
}
