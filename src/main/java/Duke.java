import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String horizontalLine = "    ___________________________________________";
    
    private static final String helloMessage = "Hello! I'm Duke";
    private static final String assistMessage = "What can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";

    private static int itemIndex = 0;
    private static HashMap<Integer,String> storage = new HashMap<Integer,String>();

    /**
     * Main function.
     */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        reply(new String[]{helloMessage, assistMessage});
        Scanner in = new Scanner(System.in);
        handleMessage(in);
    }

    /**
     * Wraps replies with horizontal lines and indentation.
     */
    public static void reply(String[] messages) {
        String combined = "";
        String indentation = "     ";
        for (String message : messages) {
            combined += indentation;
            combined += message;
            combined += "\n";
        }
        System.out.println(String.format("%s\n%s%s\n", horizontalLine, combined, horizontalLine));
    }

    /**
     * Echo messages from user.
     * If message if "list", lists all messages previously mentioned.
     * If message is "bye", exits program.
     */
    public static void handleMessage(Scanner scanner) {
        String line = scanner.nextLine();

        switch (line) {
            case "bye":
                reply(new String[]{byeMessage});
                scanner.close();
                break;
            case "list":
                reply(fetchMessages());
                handleMessage(scanner);
                break;
            default:
                saveMessage(line);
                reply(new String[]{"added: " + line});
                handleMessage(scanner);
        }
    }

    /**
     * Saves current message.
     */
    public static void saveMessage(String message) {
        itemIndex++;
        storage.put(itemIndex, message);
    }

    /**
     * Fetch all previously mentioned messages.
     */
    public static String[] fetchMessages() {
        String[] output = new String[storage.size()];
        for (int index = 0; index < storage.size(); index++) {
            String item = storage.get(index + 1);
            output[index] = String.format("%d. %s", index + 1, item);
        }
        return output;
    }
}