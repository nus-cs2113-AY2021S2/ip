import java.util.Scanner;

public class Duke {

    private static void initialiseWelcomeMessage() {
        String logo
                = "******************************************************\n"
                + "*                 Systems: [Online]                  *\n"
                + "*                Protocol: [Dominion]                *\n"
                + "*                    Race: [Terran]                  *\n"
                + "******************************************************\n";

        String greeting
                = "______________________________________________________\n"
                + "Systems Accessed...\n"
                + "Decrypting Overwrite...\n"
                + "Welcome Commander, can I be of assistance, commander?\n"
                + "______________________________________________________\n";

        System.out.println(logo);
        System.out.println(greeting);
    }

    private static void printGoodbyeMessage() {
        String goodbye
                = "______________________________________________________\n"
                + "Good Bye Commander.\n"
                + "______________________________________________________\n";

        System.out.println(goodbye);
    }

    private static String getCommand(String userInput) {
        String[] inputArray = userInput.split(" ");
        String command = inputArray[0];
        return command.toUpperCase();
    }

    private static void executeCommand(String command, String userInput) {
        String errand;
        String timestamp;
        String timestampHeader;

        switch(command) {
        case "TODO":
            errand = getSubstring(userInput);
            Task todo = new Todo(errand);
            todo.addToTaskList(errand, null); // Todo has no timestamp
            break;
        case "EVENT":
            errand = getErrand(userInput);
            timestamp = getTimestamp(userInput);
            timestampHeader = getTimestampHeader(userInput);
            Task event = new Event(errand, timestamp, timestampHeader);
            event.addToTaskList(errand, timestamp);
            break;
        case "DEADLINE":
            errand = getErrand(userInput);
            timestamp = getTimestamp(userInput);
            timestampHeader = getTimestampHeader(userInput);
            Task deadline = new Deadline(errand, timestamp, timestampHeader);
            deadline.addToTaskList(errand, timestamp);
            break;
        case "LIST":
            Task.printList();
            break;
        case "DONE":
            errand = getSubstring(userInput);
            Task.markDone(errand); // In this case, errand is the index of the item
            break;
        case "BYE":
            printGoodbyeMessage();
            break;
        }
    }

    private static String getSubstring(String userInput) {
        int spacePosition = userInput.indexOf(" ");
        String inputSubstring = userInput.substring(spacePosition+1);
        return inputSubstring;
    }

    private static String getErrand(String userInput) {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String errand = inputSubstring.substring(0, slashPosition);
        return errand.trim();
    }

    private static String getTimestamp(String userInput) {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestamp = inputSubstring.substring(slashPosition);
        int spacePosition = timestamp.indexOf(" ");
        timestamp = timestamp.substring(spacePosition);
        return timestamp.trim();
    }

    private static String getTimestampHeader(String userInput){
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestampHeader = inputSubstring.substring(slashPosition);
        int spacePosition = timestampHeader.indexOf(" ");
        timestampHeader = timestampHeader.substring(1, spacePosition);
        return timestampHeader;
    }

    /**
     * Starts the Task Manager program.
     * <p>Runs an infinite loop until "BYE" is called</p>
     */
    private static void runTaskManager() {
        String userInput;
        String command;
        Scanner in = new Scanner(System.in);

        do {
            userInput = in.nextLine();
            command = getCommand(userInput);
            executeCommand(command, userInput);
        } while (!command.equals("BYE"));
    }

    public static void main(String[] args) {
        initialiseWelcomeMessage();
        runTaskManager();
    }
}
