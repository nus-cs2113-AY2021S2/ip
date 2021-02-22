import command.CommandHandler;
import constant.Constant;
import parser.Parser;
import storage.Storage;
import java.util.Scanner;

/**
 * Main class for Mushroom Head.
 */
public class Duke {
    private static final CommandHandler commandHandler = new CommandHandler();

    /**
     * This is the start of the main program.
     * 1. initFile() will load any existing file if found.
     * 2. looper() will prompt the user for the commands to take action on.
     * 3. saveFile() will save any current data into a text file named tasks_log.txt.
     */
    public static void main(String[] args) {
        Storage.initFile();
        looper();
        Storage.saveFile();
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
            commandCode = Parser.getCommandCode(userInput);
            commandHandler.performAction(commandCode, userInput);
        } while (commandCode != Constant.INPUT_CODE_EXIT);
    }
}