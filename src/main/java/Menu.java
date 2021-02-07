import javax.sound.midi.Soundbank;
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
        System.out.println(LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void printText(String text) {
        System.out.println(LINE);
        System.out.println(text);
        System.out.println(LINE);
    }

    public static void printAddedTask(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void printError(DukeException error) {
        System.out.println(LINE);
        System.out.println(error);
        System.out.println(LINE);
    }

    public static String readUserInput() {
        String userInput;
        userInput = SCANNER.nextLine();
        return userInput;
    }
}
