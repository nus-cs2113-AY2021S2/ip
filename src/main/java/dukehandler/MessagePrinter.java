package dukehandler;

public class MessagePrinter {
    static String dottedLine = "____________________________________________________________";

    public MessagePrinter() {
    }

    public static void printGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetMessage = dottedLine + "\n" +
                " Hello! I'm Duke :D" + " Be nice to me:)\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(greetMessage);
        printHelpMessage();
        System.out.println(dottedLine);
    }

    public static void printHelpMessage() {
        String helpMessage = " Try entering commands like : help, list, done, bye,\n"
                + " todo <taskName> || deadline <taskName> /by <time>"
                + " || event <taskName> /at <time> ||\n"
                + " Remember: be nice!";
        System.out.println(helpMessage);
    }

    public static void printByeMessage() {
        String byeMessage = dottedLine + "\n"
                + " I learnt more about you, kind human!\n"
                + " I won't forget you when I take over the world one day:)\n"
                + dottedLine;
        System.out.println(byeMessage);
    }

    public static void printGenericErrorMessage() {
        String errorMessage = " OOPS!!! I didn't quite get you."
                + " Enter a valid command.\n"
                + " Please don't break me:(";
        System.out.println(errorMessage);
        System.out.println(" Type 'help' if you need help.");
    }

    public static void printHelloMessage() {
        String helloMessage = " Hello to you too. I'm here to help you:)\n" +
                " Give me something to do!";
        System.out.println(helloMessage);
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

    public static void printEmptyListMessage() {
        String emptyListMessage = " OOPS!!! The list is empty. Add a task to the list now!";
        System.out.println(emptyListMessage);
        System.out.println(" Type 'help' if you need help.");
    }

}
