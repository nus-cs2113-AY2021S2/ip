package duke.ui;

import duke.Duke;
import duke.task.Task;
import duke.exception.DukeException;

import java.util.Scanner;

public class Menu {
    private static final String LINE = "\u2500".repeat(60);
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    public static void printBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void printText(String text) {
        System.out.println(text);
    }

    public static void printAddedTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + Duke.getTaskSize() + " tasks in the list.");
    }

    public static void printError(DukeException error) {
        System.out.println(error);
    }

    public static void printDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + Duke.getTaskSize() + " tasks in the list.");
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public static String readUserInput() {
        String userInput;
        userInput = SCANNER.nextLine();
        return userInput;
    }
}
