public class Ui {
    /**
     * Constructor of Ui Class.
     */
    public Ui() {
    }

    /**
     * Prints Duke logo and welcome message.
     */
    public static void printWelcomeMessage() {
        printBorder();
        System.out.println(" Hello from");
        System.out.println("  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n");
        System.out.println(" Hello! I'm Duke.");
        System.out.println(" What can I do for you?");
        printBorder();
    }

    /**
     * Prints border that is used to outline messages to the user by duke.
     */
    public static void printBorder() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints farewell message when exiting Duke.
     */
    public static void printByeMessage() {
        printBorder();
        Storage.saveFile();
        System.out.println("     Bye. Hope to see you again soon!");
        printBorder();
    }

    /**
     * Prints message of the filepath the text file is located at.
     *
     * @param filepath filepath of the text file location used to save tasks.
     */
    public static void printFileCreatedMessage(String filepath){
        System.out.print("     I've created a text file at \" ");
        System.out.print(filepath);
        System.out.println(" \" to save your tasks!");
    }


    /**
     * Prints error message when invalid command is input by the user.
     */
    public static void printInvalidCommandMessage() {
        printBorder();
        System.out.println("     ERROR: there is no such command, try again!");
        printBorder();
    }


    /**
     * Prints error message when user does not specify the task number when inputting "done" command.
     */
    public static void printTaskUnspecifiedMessage() {
        Ui.printBorder();
        System.out.println("     ☹ OOPS!!! Please specify which task number is done");
        Ui.printBorder();
    }


    /**
     * Prints error message when user inputs a non-existent the task number in "done" command.
     */
    public static void printInvalidTask() {
        Ui.printBorder();
        System.out.println("     ☹ OOPS!!! There is no such task number");
        Ui.printBorder();
    }

    /**
     * Prints error message when user inputs a task number that has already been marked as done in "done" command.
     */
    public static void printTaskAlreadyMarkedAsDone() {
        Ui.printBorder();
        System.out.println("     ☹ OOPS!!! This task is already marked as done");
        Ui.printBorder();
    }

}
