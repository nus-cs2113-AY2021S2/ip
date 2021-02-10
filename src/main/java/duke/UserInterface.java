package duke;

public class UserInterface {

    static final String LINE_DIVIDER = "____________________________________________________________";

    public static void printBye() {
        System.out.println(LINE_DIVIDER);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }

    public static void printHello() {
        System.out.println(LINE_DIVIDER);
        System.out.println("    Hi! I'm Duke (:");
        System.out.println("    What can I do for you today?");
        System.out.println(LINE_DIVIDER);
    }
}
