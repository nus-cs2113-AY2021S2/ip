import java.util.Scanner;


public class Duke {
    /**
     * A divider (horizontal line).
     */
    public static final String DIVIDER_LINE_ONLY = "__________________________________________";
    /**
     * A divider (horizontal line) with line break at the end.
     */
    public static final String DIVIDER = "__________________________________________\n";

    /**
     * Prints greetings when the program starts.
     */
    public static void printGreetings() {
        System.out.println(
                DIVIDER
                        + " Welcome.\n"
                        + " I am your assistant Friday. ✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧ \n"
                        + " Just FYI, I am developed by Song Yu.\n"
                        + " May I know what I can help you?\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints greetings when the program ends.
     */
    public static void printExitGreetings() {
        System.out.println(
                DIVIDER
                        + " Thank you for getting in touch.\n"
                        + " See you next time. \n"
                        + "✧( ु•⌄• )◞ᴴᴬᵛᴱ ᴬ ᴳᴼᴼᴰ ᵀᴵᴹᴱ\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Echos user's input string.
     *
     * @param userInput A string input by the user.
     */
    public static void echoInput(String userInput) {
        System.out.println(
                DIVIDER
                        + userInput + "\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    public static void main(String[] args) {
        printGreetings();

        Scanner sc = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();
        String userInput;

        boolean isRunning = true;
        while (isRunning) {
            userInput = sc.nextLine();
            String[] inputWords = userInput.split(" ");
            String userCommand = inputWords[0];
            switch (userCommand) {
            case "bye":
                printExitGreetings();
                return;
            case "list":
                toDoList.printCurrentList();
                break;
            case "done":
                try {
                    int itemIndex = Integer.parseInt(inputWords[1]);
                    toDoList.updateItemStatus(itemIndex - 1, true);
                } catch (Exception e) {
                    System.out.println(DIVIDER
                            + "Invalid input!\n"
                            + "The item number should be a valid integer!\n"
                            + DIVIDER
                            + "Try again:"
                    );
                    continue;
                }
                break;
            default:
                toDoList.addListItem(userInput);
                break;
            }
        }
    }
}
