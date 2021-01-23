public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startGreetings();
        endGreetings();
    }

    public static void startGreetings() {
        String greetings = "-------------------------------------\n"
                         + "Hello! I'm Duke\n"
                         + "What can I do for you?\n\n"
                         + "-------------------------------------";
        System.out.println(greetings);
    }

    public static void endGreetings() {
        String exitStatements = "-------------------------------------\n"
                              + "Bye. Hope to see you again soon!\n"
                              + "-------------------------------------";
        System.out.println(exitStatements);
    }
}
