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
            "deadline <TASK_DESCRIPTION> /by <DEADLINE_DATE> - Create a new task with the specified description and deadline";
    public static final String EVENT_HELP_MESSAGE =
            "event <TASK_DESCRIPTION> /at <EVENT_DATE> - Create a new task with the specified description and event date";
    public static final String DELETE_HELP_MESSAGE = "delete <TASK_NUMBER> - delete the specified task";
    public static final String CLEAR_HELP_MESSAGE = "clear - deletes all tasks";

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

    public static void printWelcomeMessage() {
        printMessage("Hello! I'm Duke",
                "What can I do for you?",
                "(Type `help` for a list of commands)");
    }

    public static void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

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
                CLEAR_HELP_MESSAGE);
    }

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

    public static void printTaskAdded(String taskString, int numberOfTasks) {
        printMessage("added: " + taskString,
                getNumTasksString(numberOfTasks));
    }

    public static void printFallbackMessage() {
        printMessage("I didn't quite catch what you were saying. Please try again.",
                "Try using `help` for a list of commands.");
    }

    public static void printInvalidArgumentsMessage() {
        printMessage("That's an invalid task number!");
    }

    public static void printDukeErrorMessage(String errorMessage) {
        printMessage(errorMessage);
    }

    public static void printTaskMarkedDone(String taskString) {
        printMessage("Nice! I've marked this task as done:",
                taskString);
    }

    public static void printTaskDeleted(String taskString, int numberOfTasks) {
        printMessage("Noted. I've removed this task:",
                taskString,
                getNumTasksString(numberOfTasks));
    }

    public static void printImportErrorMessage(String errorMessage) {
        printMessage("Uh oh! I encountered an error importing your tasks",
                "Here are the details:" + errorMessage,
                "Exiting Duke now...");
    }

    public static void printExportErrorMessage(String errorMessage) {
        printMessage("Uh oh! I encountered an error exporting your tasks",
                "Here are the details: " + errorMessage,
                "Enter `bye` to try again, or `exit` to exit without saving");
    }

    public static void printTasksClearedMessage() {
        printMessage("All tasks removed!");
    }

    public static void printForceQuitErrorMessage() {
        printMessage("I didn't quite catch what you were saying. Please try again.",
                "Enter `bye` to try again, or `exit` to exit without saving");
    }

    public static void printWarnings(String[] warningsArr) {
        printMessage(warningsArr);
    }
}
