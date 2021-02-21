package ui;

public class ErrorMessagePrinter {
    static final String DOTTED_LINE = "____________________________________________________________";

    public ErrorMessagePrinter() {
    }

    public static void printGenericErrorMessage() {
        String errorMessage = " OOPS!!! I didn't quite get you."
                + " Enter a valid command.\n"
                + " Please don't break me:(";
        System.out.println(errorMessage);
        System.out.println(" Type 'help' if you need help.");
    }

    public static void printInvalidCommandMessage(String taskType) {
        String invalidCommandMessage = " OOPS!!! The command format for "
                + taskType + " is wrong.";
        System.out.println(invalidCommandMessage);
        System.out.println(" Type 'help' if you need help.");
    }

    public static void printEmptyCommandMessage(String taskType) {
        String invalidCommandMessage = " OOPS!!! The "
                + "description or time of a"
                + (taskType.equals("event") ? "n " : " ")
                + taskType + " cannot be empty.";
        System.out.println(invalidCommandMessage);
        System.out.println(" Type 'help' if you need help.");
    }

    public static void printDoneOrDeleteTaskErrorMessage(String doneOrDelete, int totalTasks) {
        System.out.println(" Hey, no funny business!\n"
                + " After the '" + (doneOrDelete) + "' command,"
                + " enter a valid integer within this range:\n"
                + " [1, " + totalTasks + "]\n"
                + " Please don't break me:( No tricks!");
        System.out.println(" Type 'list' to see all the tasks.");
        System.out.println(" Type 'help' if you need help.");
    }

    public static void printTaskAlreadyMarkedDone() {
        System.out.println(" Hey, you've already marked that as done!");
    }

    public static void printEmptyListMessage() {
        String emptyListMessage = " OOPS!!! The list is empty. Add a task to the list now!";
        System.out.println(emptyListMessage);
        System.out.println(" Type 'help' if you need help.");
    }

    public static void printIOErrorMessage() {
        String IOErrorMessage = DOTTED_LINE + "\n OOPS!!! I couldn't access your file\n" +
                " because of an IO Error. Sorry!\n" + DOTTED_LINE;
        System.out.println(IOErrorMessage);
    }
}
