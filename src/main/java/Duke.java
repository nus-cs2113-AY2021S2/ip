import java.util.Locale;
import java.util.Scanner;

public class Duke {

    static final int INPUT_CODE_EXIT = -1;
    static final int INPUT_CODE_DEFAULT = 0;
    static final int INPUT_CODE_LIST = 1;
    static final int INPUT_CODE_DONE = 2;
    static final int INPUT_CODE_INVALID = 3;

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
        String[] words = userCommand.split(" ");

        // No Fallthrough intended in this switch statement
        switch (words[0].toLowerCase()) {
        case "bye":
            return INPUT_CODE_EXIT;
        case "list":
            return INPUT_CODE_LIST;
        case "done":
            try {
                if (words.length == 1 || Integer.parseInt(words[1]) < 1) {
                    return INPUT_CODE_INVALID;
                } else {
                    return INPUT_CODE_DONE;
                }
            } catch(NumberFormatException e){
                return INPUT_CODE_INVALID;
            }
        default:
            return INPUT_CODE_DEFAULT;
        }
    }

    public static int getTaskIndex(String userInput) {
        String[] words = userInput.split(" ");
        int indexResult = Integer.parseInt(words[1]);
        return indexResult;
    }

    public static void main(String[] args) {
        printHelloStatement();

        int commandCode;
        String userInput;
        Scanner in = new Scanner(System.in);
        Task task = new Task();

        do {
            userInput = in.nextLine();
            commandCode = getCommandCode(userInput);

            // No Fallthrough intended in this switch statement
            switch (commandCode) {
            case INPUT_CODE_EXIT:
                printBye();
                break;
            case INPUT_CODE_LIST:
                printDividerLine();
                task.listTask();
                printDividerLine();
                break;
            case INPUT_CODE_DONE:
                printDividerLine();
                int index = getTaskIndex(userInput);
                task.markDone(index);
                printDividerLine();
                break;
            case INPUT_CODE_INVALID:
                printDividerLine();
                System.out.println("You have enter an invalid command, please try again.");
                printDividerLine();
                break;
            default:
                printDividerLine();
                task.addTask(userInput);
                printDividerLine();
                break;
            }
        } while (commandCode != INPUT_CODE_EXIT);
    }
}
