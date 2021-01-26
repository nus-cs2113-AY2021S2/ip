import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "    ____________________________________________________________";
    private static final String helloMessage = "Hello! I'm Duke";
    private static final String assistMessage = "What can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);

        reply(new String[]{helloMessage, assistMessage});
        
        echoUser();
    }

    /**
     * Wraps replies with horizontal lines and indentation.
     */
    public static void reply(String[] messages) {
        String combined = "";
        String indentation = "    ";
        for (String message : messages) {
            combined += indentation;
            combined += message;
            combined += "\n";
        }
        System.out.println(String.format("%s\n%s%s\n", horizontalLine, combined, horizontalLine));
    }

    /**
     * Echo messages from user.
     * If message is "bye", exits program.
     */
    public static void echoUser() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        if (line.equals("bye")) {
            reply(new String[]{byeMessage});
            in.close();
        } else {
            reply(new String[]{line});
            echoUser();
        }
    }
}
