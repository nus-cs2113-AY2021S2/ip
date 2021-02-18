import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    static Scanner scanner = new Scanner(System.in);
    static String LINEBREAK = "____________________________________________________________\n";
    static ArrayList<String> commandList = new ArrayList<String>(
            Arrays.asList("bye", "list", "done", "todo", "deadline", "event")
    );
    static String EXITCOMMAND = "bye";

    public static void printLine() {
        System.out.print(LINEBREAK);
    }

    static List list = new List();

    public static void newInstance() {
        String input = scanner.nextLine().trim();

        while (!input.equals(EXITCOMMAND)) {
            String[] stringTokens = input.split(" ", 2);
            String command = stringTokens[0];
            boolean isValidCommand = false;
            printLine();
            try {
                isValidCommand = isCommand(command);
            } catch (UnrecognizedCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (isValidCommand) {
                switch (command) {
                    case "list":
                        list.printList();
                        break;
                    case "done":
                        try {
                            list.markDone(Integer.parseInt(stringTokens[1]));
                        } catch(ArrayIndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        break;
                    case "todo":
                        try {
                            list.addTask(stringTokens[1]);
                        } catch(ArrayIndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        break;
                    case "deadline":
                        try {
                            list.addDeadline(stringTokens[1]);
                        } catch(ArrayIndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        break;
                    case "event":
                        try {
                            list.addEvent(stringTokens[1]);
                        } catch(ArrayIndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        break;
                    default:
                        break;
                }
            }
            printLine();
            input = scanner.nextLine().trim();
        }
    }

    private static boolean isCommand(String command) throws UnrecognizedCommandException {
        if (commandList.contains(command)) {
            return true;
        }
        throw new UnrecognizedCommandException();
    }

    private static class UnrecognizedCommandException extends Exception {
    }
}
