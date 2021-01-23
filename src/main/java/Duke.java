import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printGreeting();
        printLine();
        loopEcho(); // Continues to get input until "bye" is the input
        sayGoodbye();
    }

    public static void printGreeting() {
        System.out.println("Wagwan! I is Ali G. West side.");
        System.out.println("What is we chattin bout today?");
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void sayGoodbye() {
        printLine();
        System.out.println("Goodbye, big up yourself, keep it real, respekt.");
        printLine();
    }
    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        return line;
    }
    public static void loopEcho() {
        while(true) {
            String line = getInput();
            line = line.toLowerCase();
            if(line.equals("bye")) {
                return;
            }
            printLine();
            System.out.println(line);
            printLine();
        }
    }
}
