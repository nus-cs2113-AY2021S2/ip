public class Duke {

    static String LINEBREAK = "____________________________________________________________\n";
    static String LOGO = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String GREETING =  " Hello! I'm Duke\n"
            + " What can I do for you?\n";
    static String GOODBYE = "Bye, Hope to see you again soon!\n";

    public static void main(String[] args) {
        printWelcome();
        Parser.newInstance();
        printGoodbye();
    }

    public static void printWelcome() {
        System.out.println(LOGO);
        System.out.println(LINEBREAK + GREETING + LINEBREAK);
    }

    public static void printGoodbye() {
        System.out.println(LINEBREAK + GOODBYE + LINEBREAK);
    }
}
