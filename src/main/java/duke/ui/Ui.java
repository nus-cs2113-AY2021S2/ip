package duke.ui;

/**
 * Interactions with user.
 */
public class Ui {

    /**
     * Prints horizontal line.
     */
    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints starting message for Duke.
     */
    public void printStartingMessage() {
        printHorizontalLine();
        System.out.println(" Hello from");
        System.out.println("  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n");
        printHorizontalLine();
        System.out.println(" Hello! I'm Duke.");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints exit message for Duke.
     */
    public void printByeMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Prints message when duke.txt is created.
     *
     * @param filePath File path of duke.txt.
     */
    public void printFileCreatedMessage(String filePath) {
        System.out.println(" I've created a text file at " + filePath + " to save your tasks!");
    }
}
