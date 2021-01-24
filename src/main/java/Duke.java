import java.util.Locale;
import java.util.Scanner;

public class Duke {

    static final int INPUT_CODE_EXIT = -1;
    static final int INPUT_CODE_DEFAULT = 0;

    public static void printDividerLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printHelloStatement() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printDividerLine();
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDividerLine();
    }
    public static void printBye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print("____________________________________________________________");
    }
    private static int getCommandCode(String userCommand) {
        switch (userCommand.toLowerCase()) {
            case "bye":
                return INPUT_CODE_EXIT;
            default:
                return INPUT_CODE_DEFAULT;
        }
    }
    public static void echoCommand(String echoCommand) {
        System.out.println(echoCommand);
    }

    public static void main(String[] args) {
        printHelloStatement();

        int commandCode = INPUT_CODE_DEFAULT;
        String userInput;
        Scanner in = new Scanner(System.in);

        do {
            userInput = in.nextLine();
            commandCode = getCommandCode(userInput);

            switch (commandCode) {
                case INPUT_CODE_EXIT:
                    printBye();
                    break;

                default:
                    printDividerLine();
                    echoCommand(userInput);
                    printDividerLine();
                    break;
            }
        } while (commandCode != INPUT_CODE_EXIT);

        // printBye();
    }
}
