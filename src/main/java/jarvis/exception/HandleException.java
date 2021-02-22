package jarvis.exception;

import jarvis.Duke;

/**
 * Manages exceptions
 */
public class HandleException {

    /** Handles situation when task is invalid */
    public static void handleInvalidTaskException() {
        System.out.println("\tSorry, sir.");
        System.out.println("\tNo such task was found in the list.");
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
        System.out.println("\tSorry, sir. I do not recognise this command.");
        Duke.jarvis.printDivider();
    }
}
