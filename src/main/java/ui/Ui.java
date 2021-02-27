package ui;

import constant.Constant;
import task.TaskHandler;

/**
 * Ui class for handling the UI printing.
 */
public class Ui {
    private static final TaskHandler taskHandler = new TaskHandler();

    /**
     * Prints the welcome message.
     */
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
        System.out.println(Constant.DIVIDER_LINE);
        System.out.println(logo);
        System.out.println("Welcome to Track-The-Mushroom!");
        System.out.println("Mushroom Tracker is here to serve you!");
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        System.out.println(Constant.DIVIDER_LINE);
        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Prints the invalid command.
     *
     * @param invalidUserInput is the input from the console terminal entered by the user.
     */
    public static void printTaskWarningMessage(String invalidUserInput) {
        System.out.println(Constant.DIVIDER_LINE);
        System.out.println("Invalid command: " + invalidUserInput);
    }

    /**
     * Prints the task added to the list.
     *
     * @param index is the index of the task in the task list.
     */
    public static void printAddedTask(int index) {
        System.out.println("Got it. I've added this task:");
        printTaskDetails(index);
        System.out.println("Now you have " + (index + 1) + " task(s) in the list.");
    }

    /**
     * Prints all the task found in the task list.
     */
    public static void printEntireCollection() {
        if (taskHandler.getTaskCount() == 0) {
            System.out.println("You have no item in the task list!");
        } else {
            System.out.println(Constant.LIST_STARTING_MESSAGE);
            for (int i = 0; i < taskHandler.getTaskCount(); i++) {
                System.out.print((i + 1) + ".");
                printTaskDetails(i);
            }
        }
    }

    /**
     * Prints the task details such as type, completion status and description.
     *
     * @param index is the index of the task in the task list.
     */
    public static void printTaskDetails(int index) {
        printTaskSymbol(index);
        printTaskCompletionStatus(index);
        System.out.print(" ");
        printTaskDescription(index);
        System.out.println(taskHandler.extractTaskTiming(index));
    }

    /**
     * Prints the task type.
     *
     * @param index is the index of the task in the task list.
     */
    private static void printTaskSymbol(int index) {
        System.out.print("[" + taskHandler.extractTaskSymbol(index) + "]");
    }

    /**
     * Prints the task completion status.
     *
     * @param index is the index of the task in the task list.
     */
    private static void printTaskCompletionStatus(int index) {
        System.out.print("[" + taskHandler.extractTaskStatus(index) + "]");
    }

    /**
     * Prints the task description.
     *
     * @param index is the index of the task in the task list.
     */
    private static void printTaskDescription(int index) {
        System.out.print(taskHandler.extractTaskDescription(index));
    }
}
