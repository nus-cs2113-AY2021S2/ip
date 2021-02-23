package duke.ui;

import duke.common.Messages;
import duke.task.Task;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String LINE = "\u2500".repeat(60);
    private final Scanner SCANNER = new Scanner(System.in);

    public Ui() {
    }

    public void printGreeting() {
        printLine();
        printText(Messages.MESSAGE_GREETING);
        printLine();
    }

    public void printBye() {
        System.out.println(Messages.MESSAGE_BYE);
    }

    public void printText(String text) {
        System.out.println(text);
    }

    public void printAddedTask(Task task) {
        System.out.println(Messages.MESSAGE_ADDED_TASK + task);
    }

    public void printTotalTasks(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void printError(DukeException error) {
        System.out.println(error);
    }

    public void printDeletedTask(Task task) {
        System.out.println(Messages.MESSAGE_DELETED_TASK + task);
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public String readUserInput() {
        String userInput;
        userInput = SCANNER.nextLine();
        return userInput;
    }
}
