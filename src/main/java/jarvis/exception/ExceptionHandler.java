package jarvis.exception;

import jarvis.Duke;
import jarvis.commands.Help;

/**
 * Manages exceptions
 */
public class ExceptionHandler {

    /** Handles situation when task is invalid */
    public static void handleInvalidTaskException() {
        System.out.println("\tSorry sir, no such task was found in the list.");
        Duke.jarvis.printDivider();
    }

    /** Handles situation when list is empty */
    public static void handleEmptyListException() {
        System.out.println("\tYou do not have any pending task, sir.");
        Duke.jarvis.printDivider();
    }

    /** Handles situation when the text file is not found */
    public static void handleFileNotFoundException() {
        System.out.println("\tUnfortunately, I could not detect any files in the database!");
        System.out.println("\tBut don't worry sir.");
        System.out.println("\tI will create the files you might be needing later.");
        Duke.jarvis.printDivider();
    }

    /** Handles situation when user enters an invalid command */
    public static void handleInvalidCommandException() {
        System.out.println("\tSorry sir, I do not recognise this command.");
        Help.execute();
        Duke.jarvis.printDivider();
    }

    /** Handles IOException situations */
    public static void handleIOException(Exception exception) {
        System.out.println("\tSomething went wrong sir: " + exception.getMessage());
        Duke.jarvis.printDivider();
    }

    /** Handles situation when user forgets to enter the task description */
    public static void handleEmptyDescriptionException() {
        System.out.println("\tSorry sir, you need to include a description of the task as well.");
        Duke.jarvis.printDivider();
    }

    /** Handles situation when user forgets to enter a keyword */
    public static void handleEmptyKeywordException() {
        System.out.println("\tSorry sir, you need to include a keyword so that I can filter out the required tasks.");
        Duke.jarvis.printDivider();
    }

    /** Handles situation when no task in the list matches the keyword given by the user */
    public static void handleNoMatchException() {
        System.out.println("\tThere no task in the list that matches your search, sir.");
        Duke.jarvis.printDivider();
    }

    /** Handles situation when user forgets to provide the task number */
    public static void handleEmptyTaskIdException() {
        System.out.println("\tSorry sir, you need to include the task ID too.");
        Duke.jarvis.printDivider();
    }

    /** Handles situation when the user forgets to provide the task details after the description */
    public static void handleEmptyDetailException() {
        System.out.println("\tSorry sir, you need to specify the details of the task.");
        Duke.jarvis.printDivider();
    }
}
