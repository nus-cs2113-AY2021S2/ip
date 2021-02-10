public class Output {

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

    public static void printEmptyCommand(String emptyCommand) {
        System.out.println("OOPS!!! The description of a " + emptyCommand + " cannot be empty.");
    }
    public static void printWrongCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
