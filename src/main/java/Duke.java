import java.util.Scanner;
import parser.Parser;
import storage.Storage;
import ui.TextUi;
import java.io.FileNotFoundException;

public class Duke {

    private static Parser parser = new Parser();
    private static Storage storage = new Storage();
    private static TextUi textUi = new TextUi();

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Main entry point of the application.
     * Initializes the application and starts the interaction with the user.
     */
    public static void main(String[] args) {
        textUi.showGreeting();
        try {
            storage.loadFile();
        } catch (FileNotFoundException e) {
            // do nothing since no file to load
        }
        while(true) {
            String userInput = SCANNER.nextLine();
            parser.processUserInput(userInput);
        }
    }
}
