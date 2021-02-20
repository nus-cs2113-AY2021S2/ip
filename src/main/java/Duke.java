import actioner.ActionHandler;
import constant.Constant;
import parser.Parser;
import storage.Storage;
import java.util.Scanner;

public class Duke {
    private static final Constant constant = new Constant();
    private static final Storage storage = new Storage();
    private static final Parser parser = new Parser();
    private static final ActionHandler actionHandler = new ActionHandler();

    /**
     * This is the start of the main program.
     * 1. inifFile() will load any existing file if found.
     * 2. looper() will prompt the user for the commands to take action on.
     * 3. saveFile() will save any current data into a text file named tasks_log.txt.
     */
    public static void main(String[] args) {
        storage.initFile();
        looper();
        storage.saveFile();
    }

    /**
     * Prompt the user for the commands to take action on,
     * takes in the command from console terminal and execute the command,
     * stop only when it detects the exit command.
     */
    private static void looper() {
        int commandCode;
        String userInput;
        Scanner userScanner = new Scanner(System.in);
        do {
            userInput = userScanner.nextLine();
            commandCode = parser.getCommandCode(userInput);
            actionHandler.performAction(commandCode, userInput);
        } while (commandCode != constant.INPUT_CODE_EXIT);
    }
}