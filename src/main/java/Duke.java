import java.util.Scanner;

public class Duke {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        greeting();

        Scanner in = new Scanner(System.in);
        Boolean exit = false;
        while (!exit) {
            String line = in.nextLine();
            switch(line) {
            case "bye":
                exit = true;
                break;
            default:
                printLine(line);
            }
        }
        in.close();

        goodbye();
    }

    public static void greeting() {
        printIndent(LONG_LINE);
        printIndent("Hello! I'm Duke");
        printIndent("What can I do for you?");
        printIndent(LONG_LINE);
    }

    public static void goodbye() {
        printLine("Bye. Hope to see you again soon!");
    }

    protected static void printLine(String line) {
        printIndent(LONG_LINE);
        printIndent(line);
        printIndent(LONG_LINE);
    }

    protected static void printIndent(String line) {
        System.out.println("    " + line);
    }
}
