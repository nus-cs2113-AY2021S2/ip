package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    public  void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printDone(Task task) {
        System.out.println("This task has been done. Good job!");
        task.printDescription();
        System.out.print("\n");
    }

    public static void printDeleted() {
        System.out.println("The task has been deleted.");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hi! I'm Duke UwU");
        System.out.println("What can I do for you today?");
        showLine();
    }

    public static void printCommandIsInvalid() {
        System.out.println("Invalid command! Please try again.");
    }

    public static void listIsEmpty() {
        System.out.println("List is empty!");
    }

    public static void printTaskSize() {
        if (TaskList.getListSize() == 1) {
            System.out.println(TaskList.getListSize() + " task left in the list");
        } else {
            System.out.println(TaskList.getListSize() + " tasks left in the list");
        }
    }

    public static void printMatchedIndex(int index) {
        System.out.print(index + ".");
    }

    public static void printTodoDescription() {
        System.out.println("Gotcha! I've added this task:");
        System.out.print("[T]");
    }

    public static void printDeadlineDescription() {
        System.out.println("Gotcha! I've added this task:");
        System.out.print("[D]");
    }

    public static void printEventDescription() {
        System.out.println("Gotcha! I've added this task:");
        System.out.print("[E]");
    }

    public static void printAtDescription() {
        System.out.println("(at:" + Event.getAt() + ")");
    }

    public static void printByDescription() {
        System.out.println("(by:" + Deadline.getBy() + ")");
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() throws FileNotFoundException {
        String loadedCommand;
        Scanner input = new Scanner(System.in);
        loadedCommand = input.nextLine();
        return loadedCommand;
    }

    public void printLoadError() {
        System.out.println("Oh no! Error in loading tasks");
    }

    public static void printSaveError() {
        System.out.println("Warning! Save failed");
    }

    public void showError(String message) {
        System.out.println("Warning! Error: " + message);
    }
}
