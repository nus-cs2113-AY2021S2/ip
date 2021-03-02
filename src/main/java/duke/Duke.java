package duke;

import duke.parser.Parser;
import duke.ui.TextUi;

import static duke.storage.Storage.loadFile;
import static duke.storage.Storage.saveFile;


public class Duke {
    public final TaskList taskList;
    private TextUi ui;

    public Duke() {
        ui = new TextUi();
        taskList = new TaskList();
    }

    /**
     * Main entry point of the application.
     * Initializes the application and starts the interaction with the user.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        saveFile(taskList);
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText  = ui.scanInput();
            command = Parser.processInput(taskList, userCommandText);
            ui.printReaction(taskList, command, userCommandText);
        } while (!ui.isExit(command));
    }

    private void start() {
        ui.printInitialMsg();
        loadFile(taskList);
    }
}