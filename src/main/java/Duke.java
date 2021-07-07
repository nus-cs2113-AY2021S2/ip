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
     * Starts the application by calling main.
     */
    public static void main(String[] args) {
        Storage.initFile();
        loopForUserInput();
        Storage.saveFile();
    }

    /**
     * Loop for the user input.
     */
    private static void loopForUserInput() {
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