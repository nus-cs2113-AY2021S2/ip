package duke;

import duke.exceptions.InvalidCommandException;
import duke.parser.Parser;
import duke.ui.TextUi;

import java.io.IOException;

import static duke.storage.Storage.loadFile;
import static duke.storage.Storage.saveFile;


public class Duke {
    public TaskList taskList;
    private TextUi ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program until termination.
     */
    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void exit() {
        try {
            saveFile(taskList);
        } catch (IOException | SecurityException e) {
            System.out.println("Unable to save records into file. Duke exiting.");
        }
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.scanInput();
            command = Parser.processInput(taskList, userCommandText);
            ui.printReaction(taskList, command, userCommandText);
        } while (!ui.isExit(command));
    }

    private void start() {
        this.ui = new TextUi();
        this.taskList = new TaskList();
        ui.printInitialMsg();
        try {
            loadFile(taskList);
        } catch (IOException | InvalidCommandException | SecurityException e) {
            System.out.println("Unable to load records from a file. Continuing Duke with an empty record.");
        }
    }
}