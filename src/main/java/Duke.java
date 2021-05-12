import java.util.ArrayList;
import task.*;

import tasklist.TaskList;
import ui.TextUi;
import parser.Parser;
import storage.Storage;

/**
 * Entry point of the Address Book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private static final String EXIT_COMMAND = "bye";

    public static ArrayList<Task> allTasks = new ArrayList<>();

    /**
     *  Sets up the task list, loads up the existing tasks from the storage file,
     *  and prints the welcome message.
     */
    public static void main(String[] args) {
        TaskList taskList = new TaskList(allTasks);
        String filePath = "/Users/yanyixue/Desktop/year3/sem2/CS2113/ip/tasks.txt";
        Storage storage = new Storage(filePath);
        run();
    }

    /**
     * Runs the program until termination.
     */
    private static void run() {
        TextUi.showWelcomeMessage();
        TaskList.allTasks = Storage.getSavedTasks();
        runLoopUntilExitCommand();
        exit();
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private static void exit() {
        TextUi.showGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private static void runLoopUntilExitCommand() {
        String userInput;
        do {
            userInput = TextUi.getUserCommand();
            Parser.parseCommand(userInput);

        } while (!userInput.equalsIgnoreCase(EXIT_COMMAND));
    }
}


