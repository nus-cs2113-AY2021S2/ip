package duke;

import storage.Storage;
import ui.TextUi;
import parser.Parser;


/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    private TextUi ui;
    private Storage storage;


    public static void main(String... args) {
        new Main().run();
    }

    /** Runs the program until termination. */
    private void run() {
        start();
        runCommandLoopUntilByeCommand();
        exit();
    }

    /** Sets up the required objects, loads up the data from the storage file, and prints the welcome message. */
    private void start() {
        ui = new TextUi();
        ui.showWelcomeMessage();
        storage = new Storage();
        storage.readFile();
    }

    /** Reads the user command and executes it, until the user issues the bye command. */
    private void runCommandLoopUntilByeCommand() {

        while (true) {
            String userCommandText = ui.getUserCommand();
            Parser.parseCommand(userCommandText);

            if (userCommandText.equals("bye")) {
                break;
            }
        }
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
