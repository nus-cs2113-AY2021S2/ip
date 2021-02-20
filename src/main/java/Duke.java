import actioner.ActionHandler;
import constant.Constant;
import parser.Parser;
import storage.Storage;
import java.util.Scanner;

public class Duke {
    // Variable Used in this program
    private static final Constant constant = new Constant();
    private static final Storage storage = new Storage();
    private static final Parser parser = new Parser();
    private static final ActionHandler actionHandler = new ActionHandler();

    // main program
    public static void main(String[] args) {
        storage.initFile();
        looper();
        storage.saveFile();
    }

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