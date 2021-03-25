package duke;

public class Ui {

    /**
     * Prints Duke logo and welcome message
     * when the user starts Duke.
     */
    public static void printStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printBorder() {
        System.out.println("------------------------------------");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints error message when command is empty.
     *
     * @param emptyCommand Command that follows an empty user input.
     */
    public static void printEmptyCommand(String emptyCommand) {
        System.out.println("OOPS!!! The description of a " + emptyCommand + " cannot be empty.");
    }

    /**
     * Prints error message when command is not any operations define in TaskList.
     */
    public static void printWrongCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void errorMessageDuringFileCreation() {
        System.out.println("There was an error when creating file. Try again!");
    }

    /**
     * Prints error message when command is missing description(s).
     */
    public static void printErrorMessageOnMissingTask() {
        System.out.println("Incomplete command!");
        System.out.println("You may be missing some description.");
    }

    /**
     * Prints error message if only whitespaces is entered as an input.
     */
    public static void printErrorMessageOnWhitespaces() {
        System.out.println("Please key in a valid command instead of whitespaces!");
    }

    /**
     * Prints error message when the command by the user
     * does not follow the slash format for adding Event task and Deadline task.
     *
     * @param command
     * @param slashString
     */
    public static void printWrongSlashInput(String command, String slashString) {
        System.out.println("Incorrect Slash input! " + "Try again! Do "
                + command + " <something> " + slashString + " <something>");
    }

    /**
     * Prints error message when user key in number not present in the list.
     * This error message is applicable for command which requires numbering of a task,
     * naming command done and delete.
     */
    public static void printWrongIndexMessage() {
        System.out.println("No index found. Please key an appropriate index");
    }

    public static void printAddTaskMessage() {
        System.out.println("Got it. I've added this task: ");
    }

    /**
     * Prints message when a new text file is created
     * that is used to store list of tasks.
     *
     * @param absolutePath
     */
    public static void fileCreatedMessage(String absolutePath) {
        System.out.println("file created at current location: \n"
                + String.format(absolutePath));
    }

    /**
     * Prints error message when unable to update the list of tasks
     * stored in text file created before.
     */
    public static void printErrorMessageWritingToFile() {
        System.out.println("Error while writing to file. Try again!");
    }

    /**
     * Prints error message when reading the file
     * with the specified pathname does not exist.
     */
    public static void printFileNotFoundExceptionMessage() {
        System.out.println("File not found. An attempt to open the file has failed.");
    }
}
