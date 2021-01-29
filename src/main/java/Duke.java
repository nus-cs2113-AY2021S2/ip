import java.util.Scanner;
import java.util.Vector;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static Vector<String> entries = new Vector<String>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        while(true) {
            String userCommand = getUserInput();
            if (userCommand.equalsIgnoreCase("bye")){
                exitDuke();
                break;
            }
            if (userCommand.equalsIgnoreCase("list")){
                listOutEntries();
            } else {
            printLine();
            addUserCommandToEntries(userCommand);
            }
            printLine();
        }
    }

    private static void exitDuke() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        return;
    }

    private static void printLine() {
        System.out.println("---------------------------------------------------");
    }

    private static void addUserCommand(String userCommand) {
        System.out.println(userCommand);
    }

    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        return inputLine;
    }

    private static void addUserCommandToEntries(String userCommand) {
        String newEntries = userCommand;
        entries.add(userCommand);
        System.out.println("Added: " + userCommand);
    }

    private static void listOutEntries() {
        int i = 0;
        while (i < entries.size()) {
            System.out.println(i + ". " + entries.get(i));
            i++;
        }
    }
}
