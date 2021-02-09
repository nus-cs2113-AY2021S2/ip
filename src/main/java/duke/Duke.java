package duke;

import duke.exception.IllegalTaskCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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

    private static void executeCommand(String command, String userInput) throws IllegalTaskCommandException {
        String errand;
        String timestamp;
        String timestampHeader;

        switch (command) {
        case "TODO":
            errand = getSubstring(userInput);
            Task todo = new Todo(errand);
            todo.addToTaskList(errand, null); // Todo has no timestamp
            break;
        case "EVENT":
            errand = getErrand(userInput);
            timestampHeader = getTimestampHeader(userInput);
            timestamp = getTimestamp(userInput);
            Task event = new Event(errand, timestamp, timestampHeader);
            event.addToTaskList(errand, timestamp);
            break;
        case "DEADLINE":
            errand = getErrand(userInput);
            timestampHeader = getTimestampHeader(userInput);
            timestamp = getTimestamp(userInput);
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
        default:
            throw new IllegalTaskCommandException("Unacceptable Command!");
        }
    }

    private static String getSubstring(String userInput) throws IllegalTaskCommandException {
        String cleanUserInput = userInput.strip(); // Removes any leading and trailing spaces
        int spacePosition = cleanUserInput.indexOf(" ");
        if (spacePosition < 0) { // Is single command
            throw new IllegalTaskCommandException("Insufficient command parameters commander!");
        }
        return cleanUserInput.substring(spacePosition + 1);
    }

    private static String getErrand(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        if (slashPosition < 0) {
            throw new IllegalTaskCommandException("Timestamp not found commander!");
        }
        String errand = inputSubstring.substring(0, slashPosition);
        return errand.trim();
    }

    private static String getTimestampHeader(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestampHeader = inputSubstring.substring(slashPosition);
        int spacePosition = timestampHeader.indexOf(" ");
        if (timestampHeader.split(" ").length < 2) {
            throw new IllegalTaskCommandException("Missing timestamp commander!");
        }
        timestampHeader = timestampHeader.substring(1, spacePosition);
        return timestampHeader;
    }

    private static String getTimestamp(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestamp = inputSubstring.substring(slashPosition);
        int spacePosition = timestamp.indexOf(" ");
        timestamp = timestamp.substring(spacePosition);
        return timestamp.trim();
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

            try {
                executeCommand(command, userInput);
            } catch (IllegalTaskCommandException e) {
                e.printErrorLogo();
                System.err.println(e);
            }

        } while (!command.equals("BYE"));
    }

    public static void main(String[] args) {
        initialiseWelcomeMessage();
        runTaskManager();
    }
}
