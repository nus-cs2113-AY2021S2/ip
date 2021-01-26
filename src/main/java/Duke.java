import java.util.Scanner;


public class Duke {
    public static final String DIVIDER_LINE_ONLY = "__________________________________________";
    public static final String DIVIDER = "__________________________________________\n";

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

    public static void printExitGreetings() {
        System.out.println(
                DIVIDER
                        + " Thank you for getting in touch.\n"
                        + " See you next time. \n"
                        + "✧( ु•⌄• )◞ᴴᴬᵛᴱ ᴬ ᴳᴼᴼᴰ ᵀᴵᴹᴱ\n"
                        + DIVIDER_LINE_ONLY
        );
    }

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

        // boolean variable to control the input loop
        boolean isRunning = true;
        while (isRunning) {
            // get user input
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
