import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Initialize data
        Vector<String> savedList = new Vector<>();

        greeting();

        Scanner in = new Scanner(System.in);
        Boolean exit = false;
        while (!exit) {
            String line = in.nextLine();
            switch(line) {
            case "bye":
                exit = true;
                break;
            case "list":
                // Print out everything in the list, index starts from 1
                printIndent(LONG_LINE);
                for (int i = 0; i < savedList.size(); i += 1) {
                    printIndent(String.format("%d. %s", i+1, savedList.get(i)));;
                }
                printIndent(LONG_LINE);
                break;
            default:
                // Save the line in the list
                savedList.add(line);
                printLine("added: " + line);
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
