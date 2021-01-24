import java.util.Scanner;

public class Duke {
    
    public static String HORIZONTAL_LINE = "____________________________________________________________";
    /**
     * Handles commands from user
     * @param command command from the user
     */
    public static void handleCommand(String command) {
        switch (command) {
        case "bye":
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL_LINE);
            break;
        default:
            System.out.println(HORIZONTAL_LINE);
            System.out.println(command);
            System.out.println(HORIZONTAL_LINE);
            break;
        }
    }
    public static void main(String[] args) {
        // logo/loading
        System.out.println(HORIZONTAL_LINE);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);

        // greeting
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(HORIZONTAL_LINE);

        String command; // receiving command input
        boolean shouldExit = false;
        Scanner in = new Scanner(System.in);
        while (!shouldExit) {
            command = in.nextLine();
            if (command.equals("bye")) {
                handleCommand(command);
                shouldExit = true;
            } else {
                handleCommand(command);
            }
        }
    }
}
