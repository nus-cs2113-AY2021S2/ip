public class MessagePrinter {
    public MessagePrinter() {
    }
    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"

                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBorder();
        System.out.println("     Hello! I'm Duke ");
        System.out.println("     What can I do for you?");
        printBorder();
    }
    public static void printBorder() {
        System.out.println("    ____________________________________________________________");
    }

    public static void showByeMessage() {
        printBorder();
        System.out.println("     Bye. Hope to see you again soon!");
        printBorder();
    }
    public static void showInvalidCommandMessage(){
        printBorder();
        System.out.println("     ☹ OOPS!!! That is an invalid command.");
        printBorder();
    }

}
