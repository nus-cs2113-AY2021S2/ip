package duke;

import java.util.ArrayList;

public class DukePrinter {

    private static final String LINE_DIVIDER = "\t____________________________________________________________";

    /* List of help messages for each command*/
    public static final String BYE_HELP_MESSAGE = "bye - Exits program";
    public static final String HELP_HELP_MESSAGE = "help - Prints this help message";
    public static final String LIST_HELP_MESSAGE = "list - Lists all tasks";
    public static final String DONE_HELP_MESSAGE = "done <TASK_NUMBER> - Mark the specified task as done";
    public static final String TODO_HELP_MESSAGE =
            "todo <TASK_DESCRIPTION> - Create a new task with the specified description";
    public static final String DEADLINE_HELP_MESSAGE =
            "deadline <TASK_DESCRIPTION> /by <DEADLINE_DATE> - Create a new task with the specified description and deadline\n" +
                    "\t\tFormat for DEADLINE_DATE: YYYY-MM-DD";
    public static final String EVENT_HELP_MESSAGE =
            "event <TASK_DESCRIPTION> /at <EVENT_DATE> - Create a new task with the specified description and event date\n" +
                    "\t\tFormat for EVENT_DATE: YYYY-MM-DD";
    public static final String DELETE_HELP_MESSAGE = "delete <TASK_NUMBER> - delete the specified task";
    public static final String CLEAR_HELP_MESSAGE = "clear - deletes all tasks";
    public static final String FIND_HELP_MESSAGE =
            "find <SEARCH_EXPRESSION> - finds all tasks containing the required expression";

    private static void printMessage(String... messages) {
        System.out.println(LINE_DIVIDER);
        for (String message : messages) {
            System.out.println("\t " + message);
        }
        System.out.println(LINE_DIVIDER);
    }

    private static String getNumTasksString(int numberOfTasks) {
        if (numberOfTasks == 1) {
            return "You have " + Integer.toString(numberOfTasks) + " task in the list.";
        }
        return "You have " + Integer.toString(numberOfTasks) + " tasks in the list.";
    }

    /**
     * Prints a welcome message to indicate that Duke has started.
     * It also informs the user that they can use the `help` command to find a list of valid commands.
     * This method returns after printing the message.
     */
    public static void printWelcomeMessage() {
        printMessage("Hello! I'm Duke",
                "What can I do for you?",
                "(Type `help` for a list of commands)");
    }

    /**
     * Prints a welcome message to indicate that Duke has exited.
     * This method returns after printing the message.
     */
    public static void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a list of valid commands as well as the required arguments.
     * In the case that the arguments require a specific format, it also indicates the format to be used.
     * (e.g. The date format for the `deadline` and `event` commands)
     */
    public static void printHelpMessage() {
        printMessage("List of valid commands:",
                BYE_HELP_MESSAGE,
                HELP_HELP_MESSAGE,
                LIST_HELP_MESSAGE,
                DONE_HELP_MESSAGE,
                TODO_HELP_MESSAGE,
                DEADLINE_HELP_MESSAGE,
                EVENT_HELP_MESSAGE,
                DELETE_HELP_MESSAGE,
                CLEAR_HELP_MESSAGE,
                FIND_HELP_MESSAGE);
    }


    /**
     * Prints an enumerated list of the user's current tasks.
     * If the user has no tasks (i.e. the ArrayList is empty), this method will print a message to inform them that
     * they have no tasks.
     *
     * @param taskStrings an ArrayList of Strings containing the task information
     */
    public static void printTasks(ArrayList<String> taskStrings) {
        int numberOfTasks = taskStrings.size();
        String[] taskMessage = new String[numberOfTasks + 1];
        if (taskStrings.size() == 0) {
            taskMessage[0] = "You have no tasks right now";
        } else {
            taskMessage[0] = "Here is a list of all your tasks:";
            for (int i = 0; i < taskStrings.size(); i++) {
                taskMessage[i + 1] = Integer.toString(i + 1) + ". " + taskStrings.get(i);
            }
        }
        printMessage(taskMessage);
    }

    /**
     * Prints a message to inform the user that the task has been successfully added.
     *
     * @param taskString    information of the recently added task
     * @param numberOfTasks the total number of tasks that the user has
     */
    public static void printTaskAdded(String taskString, int numberOfTasks) {
        printMessage("added: " + taskString,
                getNumTasksString(numberOfTasks));
    }

    /**
     * Prints a message to inform the user that the most recent command was not understood.
     * Prompts the user to try the `help` command if they are not familiar with the valid commands.
     */
    public static void printFallbackMessage() {
        printMessage("I didn't quite catch what you were saying. Please try again.",
                "Try using `help` for a list of commands.");
    }

    /**
     * Prints a message to inform the user that the arguments specified were not valid.
     * (e.g. Entering non-numeric data as an argument for TASK_NUMBER)
     */
    public static void printInvalidArgumentsMessage() {
        printMessage("That's an invalid task number!");
    }

    /**
     * Prints an error message to inform the user that an error has occurred.
     *
     * @param errorMessage the error message
     */
    public static void printDukeErrorMessage(String errorMessage) {
        printMessage(errorMessage);
    }

    /**
     * Prints a message to inform the user that the specified task has been successfully marked as done.
     *
     * @param taskString information of the task recently marked as done
     */
    public static void printTaskMarkedDone(String taskString) {
        printMessage("Nice! I've marked this task as done:",
                taskString);
    }

    /**
     * Prints a message to inform the user that the specified task has been successfully deleted.
     *
     * @param taskString information of the recently deleted task
     */
    public static void printTaskDeleted(String taskString, int numberOfTasks) {
        printMessage("Noted. I've removed this task:",
                taskString,
                getNumTasksString(numberOfTasks));
    }

    /**
     * Prints a message to inform the user that the Duke has encountered an error importing data.
     * It also informs the user that Duke will immediately exit.
     *
     * @param errorMessage the error message
     */
    public static void printImportErrorMessage(String errorMessage) {
        printMessage("Uh oh! I encountered an error importing your tasks",
                "Here are the details:" + errorMessage,
                "Exiting Duke now...");
    }

    /**
     * Prints a message to inform the user that the Duke has encountered an error exporting data.
     * It prompts the user to either use the `bye` command to try exporting again, or use `exit` to exit without saving
     *
     * @param errorMessage the error message
     */
    public static void printExportErrorMessage(String errorMessage) {
        printMessage("Uh oh! I encountered an error exporting your tasks",
                "Here are the details: " + errorMessage,
                "Enter `bye` to try again, or `exit` to exit without saving");
    }

    /**
     * Prints a message to inform the user that all tasks have been cleared.
     */
    public static void printTasksClearedMessage() {
        printMessage("All tasks removed!");
    }

    /**
     * Prints a message to inform the user that the most recent command was not understood.
     * Prompts them to use one of the two valid commands (`bye` or `exit`).
     */
    public static void printForceQuitErrorMessage() {
        printMessage("I didn't quite catch what you were saying. Please try again.",
                "Enter `bye` to try again, or `exit` to exit without saving");
    }

    /**
     * Prints a list of the warnings encountered while importing data from the file.
     * If there are no warnings (i.e. the ArrayList is empty), this method will print a message to inform them that
     * importing was successful
     *
     * @param warnings an ArrayList of Strings containing warnings
     */
    public static void printWarnings(ArrayList<String> warnings) {
        String[] warningsArr = new String[warnings.size() + 1];
        if (warnings.isEmpty()) {
            warningsArr[0] = "All tasks imported successfully!";
        } else {
            warningsArr[0] = "Warning: Failed to import " + Integer.toString(warnings.size()) + " task(s)";
            for (int i = 0; i < warnings.size(); i++) {
                warningsArr[i + 1] = warnings.get(i);
            }
        }
        printMessage(warningsArr);
    }

    /**
     * Prints an enumerated list of tasks that match the user's search criteria
     * If the user has no matching tasks (i.e. the ArrayList is empty), this method will print a message to inform them
     * that they have no matching tasks.
     *
     * @param matchingTasks an ArrayList of Strings containing the task information of all tasks that match the search
     *                      criteria
     */
    public static void printMatchingTasks(ArrayList<String> matchingTasks) {
        String[] matchingTaskArr = new String[matchingTasks.size() + 1];
        if (matchingTasks.isEmpty()) {
            matchingTaskArr[0] = "No matching tasks found!";
        } else {
            matchingTaskArr[0] = "Here are the matching tasks in your list:";
            for (int i = 0; i < matchingTasks.size(); i++) {
                matchingTaskArr[i + 1] = Integer.toString(i + 1) + ". " + matchingTasks.get(i);
            }
        }
        printMessage(matchingTaskArr);
    }
}
