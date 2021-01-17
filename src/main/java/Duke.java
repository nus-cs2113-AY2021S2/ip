public class Duke {
    private static final String breakLine = "____________________________________________________________";

    public static void showLogo() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(breakLine + "\n" + logo + "\n" + breakLine);
    }
    public static void greetMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(breakLine);
    }

    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(breakLine);
    }
    public static void main(String[] args) {
        showLogo();
        greetMessage();
        byeMessage();
    }
}
