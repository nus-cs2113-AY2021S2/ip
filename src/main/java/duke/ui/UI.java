package duke.ui;

public class UI {

    /**
     * A divider (horizontal line) with line break at the end.
     */
    public static final String DIVIDER = "_____________________________________________________________\n";
    /**
     * A divider (horizontal line).
     */
    public static final String DIVIDER_LINE_ONLY = "_____________________________________________________________";

    /**
     * Prints greetings when the program starts.
     */
    public static void printGreetings() {
        System.out.println(
                DIVIDER
                        + " Welcome.\n"
                        + " I am your assistant Friday. ✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧\n"
                        + " Just FYI, I am developed by Song Yu.\n"
                        + " May I know what I can help you?\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints greetings when the program ends.
     */
    public static void printExitGreetings() {
        System.out.print(
                DIVIDER
                        + " Thank you for getting in touch.\n"
                        + " See you next time.\n"
                        + "✧( ु•⌄• )◞ᴴᴬᵛᴱ ᴬ ᴳᴼᴼᴰ ᵀᴵᴹᴱ\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints the error message when user's input does not match any command.
     */
    public static void printNotCommand() {
        System.out.println(
                DIVIDER
                        + " Sorry, your command is not recognized\n"
                        + " See 'help'. \n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints the help message.
     */
    public static void printHelp() {
        String helpMessage = " 'help'     : Display tips on using this application\n"
                + " 'exit'     : Exit the application\n"
                + " 'bye'      : Exit the application\n"
                + " 'list'     : List all type of tasks you added to your task list\n"
                + " 'done'     : Mark a task as done\n\t"
                + " e.g. 'done 2' will mark the second task as done\n"
                + " 'todo'     : Add a new todo task\n\t"
                + " e.g. 'todo read book' will add \"read book\" to your task list\n"
                + " 'deadline' : Add a new deadline task\n\t"
                + " e.g. 'deadline read book /by YYYY-DD-MM' will"
                + " add \"read book\" to your task list with deadline task by date in format YYYY-DD-MM.\n"
                + " 'event' : Add a new event task\n\t"
                + " e.g. 'event read book /at YYYY-DD-MM' will"
                + " add \"read book\" to your task list with an event task at date in format YYYY-DD-MM.\n";

        System.out.println(
                DIVIDER_LINE_ONLY + DIVIDER
                        + helpMessage
                        + DIVIDER_LINE_ONLY + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints a warning that the input is not a valid integer.
     */
    public static void printInvalidIntegerWarning() {
        System.out.println(DIVIDER
                + "Invalid input!\n"
                + "The item number should be a valid integer!\n"
                + DIVIDER
                + "Try again:"
        );
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(DIVIDER + e.getMessage() + "\n" + DIVIDER_LINE_ONLY);
    }

    public static void printParseDateError() {
        System.out.println(DIVIDER
                + "The time format you input is incorrect or out of range!\n"
                + "The format is in YYYY-MM-DD. Please try again.\n"
                + DIVIDER_LINE_ONLY);
    }
}
