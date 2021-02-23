package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* This class handles printing outputs to screen */
public class DukePrinter {

    private static final String LINE_DIVIDER = "\t____________________________________________________________";

    public static void printMessage(String... messageSentences) {
        System.out.println(LINE_DIVIDER);
        for (int i = 0; i < messageSentences.length; i++) {
            System.out.println("\t " + messageSentences[i]);
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
        String[] welcomeMessage = {
                "Hello! I'm Duke",
                "What can I do for you?",
                "(Type 'help' for a list of commands)"};
        printMessage(welcomeMessage);
    }

    public static void printExitMessage() {
        String[] exitMessage = {
                "Bye. Hope to see you again soon!"};
        printMessage(exitMessage);
    }

    public static void printHelpMessage() {
        String[] helpMessage = {
                "List of valid commands:",
                DukeCommands.BYE_HELP_MESSAGE,
                DukeCommands.HELP_HELP_MESSAGE,
                DukeCommands.LIST_HELP_MESSAGE,
                DukeCommands.DONE_HELP_MESSAGE,
                DukeCommands.TODO_HELP_MESSAGE,
                DukeCommands.DEADLINE_HELP_MESSAGE,
                DukeCommands.EVENT_HELP_MESSAGE,
                DukeCommands.DELETE_HELP_MESSAGE,
                DukeCommands.CLEAR_HELP_MESSAGE
        };
        printMessage(helpMessage);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        String[] taskMessage = new String[tasks.size() + 1];
        if (tasks.size() == 0) {
            taskMessage[0] = "You have no tasks right now";
        } else {
            taskMessage[0] = "Here is a list of all your tasks:";
            for (int i = 0; i < tasks.size(); i++) {
                taskMessage[i + 1] = Integer.toString(i + 1) + ". " + tasks.get(i);
            }
        }
        printMessage(taskMessage);
    }

    public static void printTaskAdded(Task task, int numberOfTasks) {
        String[] taskAddedMessage = {
                "added: " + task.toString(),
                getNumTasksString(numberOfTasks)
        };
        printMessage(taskAddedMessage);
    }

    public static void printFallbackMessage() {
        String[] fallbackMessage = {
                "I didn't quite catch what you were saying. Please try again.",
                "Try using \"help\" for a list of commands."
        };
        printMessage(fallbackMessage);
    }

    public static void printInvalidArgumentsMessage() {
        String[] invalidArgumentsMessage = {
                "That's an invalid task number!"
        };
        printMessage(invalidArgumentsMessage);
    }

    public static void printDukeErrorMessage(String errorMessage) {
        String[] errorMessages = {
                errorMessage,
        };
        printMessage(errorMessages);
    }

    public static void printTaskMarkedDone(Task task) {
        String[] taskDoneMessages = {
                "Nice! I've marked this task as done:",
                task.toString()
        };
        printMessage(taskDoneMessages);
    }

    public static void printTaskDeleted(Task task, int numberOfTasks) {
        String[] taskDeletedMessages = {
                "Noted. I've removed this task:",
                task.toString(),
                getNumTasksString(numberOfTasks)
        };
        printMessage(taskDeletedMessages);
    }

    public static void printExportErrorMessage(String errorMessage) {
        String[] exportErrorMessages = {
                "Uh oh! I encountered an error exporting your tasks",
                "Here are the details:",
                errorMessage
        };
        printMessage(exportErrorMessages);
    }

    public static void printImportErrorMessage(String errorMessage) {
        String[] exportErrorMessages = {
                "Uh oh! I encountered an error importing your tasks",
                "Here are the details:",
                errorMessage
        };
        printMessage(exportErrorMessages);
    }

    public static void printTasksClearedMessage() {
        String[] taskClearedMessages = {
                "All tasks removed!"
        };
        printMessage(taskClearedMessages);
    }
}
