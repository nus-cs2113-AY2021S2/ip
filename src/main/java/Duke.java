import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

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
            printLine();
            echoUserCommand(userCommand);
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

    private static void echoUserCommand(String userCommand) {
        System.out.println(userCommand);
    }

    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        return inputLine;
    }
}
