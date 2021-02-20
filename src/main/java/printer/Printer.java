package printer;

import constant.Constant;
import task.TaskHandler;

public class Printer {
    private static final Constant constant = new Constant();
    private static final TaskHandler taskHandler = new TaskHandler();

    public static void printWelcomeMessage() {
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
        System.out.println(constant.DIVIDER_LINE);
        System.out.println(logo);
        System.out.println("Welcome Mushroom!");
        System.out.println("Mushroom boy is here to serve you!");
        System.out.println(constant.DIVIDER_LINE);
    }

    public static void printExitMessage() {
        System.out.println(constant.DIVIDER_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(constant.DIVIDER_LINE);
    }

    public static void printTaskWarningMessage(String invalidUserInput) {
        System.out.println(constant.DIVIDER_LINE);
        System.out.println("Invalid command: " + invalidUserInput);
    }

    public static void printAddedTask(int index) {
        System.out.println("Got it. I've added this task:");
        printTaskDetails(index);
        System.out.println("Now you have " + (index + 1) + " tasks in the list.");
    }

    public static void printEntireCollection() {
        if (taskHandler.getTaskCount() == 0) {
            System.out.println("You have no item in Mushroom Head list!");
        } else {
            System.out.println(constant.LIST_STARTING_MESSAGE);
            for (int i = 0; i < taskHandler.getTaskCount(); i++) {
                System.out.print(i + 1 + ".");
                printTaskDetails(i);
            }
        }
    }

    public static void printTaskDetails(int index) {
        printTaskSymbol(index);
        printTaskCompletionStatus(index);
        System.out.print(" ");
        printTaskDescription(index);
        System.out.println(taskHandler.extractTaskTiming(index));
    }

    private static void printTaskSymbol(int index) {
        System.out.print("[" + taskHandler.extractTaskSymbol(index) + "]");
    }

    private static void printTaskCompletionStatus(int index) {
        System.out.print("[" + taskHandler.extractTaskStatus(index) + "]");
    }

    private static void printTaskDescription(int index) {
        System.out.print(taskHandler.extractTaskDescription(index));
    }
}
