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
        String logo = "           ____\n" +
                "       _.-'78o `\"`--._\n" +
                "   ,o888o.  .o888o,   ''-.\n" +
                " ,88888P  `78888P..______.]\n" +
                "/_..__..----\"\"        __.'\n" +
                "`-._       /\"\"| _..-''\n" +
                "    \"`-----\\  `\\\n" +
                "            |   ;.-\"\"--..\n" +
                "            | ,8o.  o88. `.\n" +
                "            `;888P  `788P  :\n" +
                "      .o\"\"-.|`-._         ./\n" +
                "     J88 _.-/    \";\"-P----'\n" +
                "     `--'\\`|     /  /\n" +
                "         | /     |  |\n" +
                "         \\|     /   |akn\n" +
                "          `-----`---'\n";
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        printDividerLine();
        System.out.println(logo);
        System.out.println("Welcome Mushroom!");
        System.out.println("What can Mushroom Head do for you?");
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

    private static void performAction(int commandCode, String userInput, Task task) {
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
            int index = getTaskIndexFromUserInput(userInput);
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
    }

    private static int getTaskIndexFromUserInput(String userInput) {
        String[] words = userInput.split(" ");
        int indexResult = Integer.parseInt(words[1]);
        return indexResult;
    }

    public static void main(String[] args) {
        int commandCode;
        String userInput;
        Scanner in = new Scanner(System.in);
        Task task = new Task();

        printHelloStatement();
        do {
            userInput = in.nextLine();
            commandCode = getCommandCode(userInput);
            performAction(commandCode, userInput, task);
        } while (commandCode != INPUT_CODE_EXIT);
    }
}
