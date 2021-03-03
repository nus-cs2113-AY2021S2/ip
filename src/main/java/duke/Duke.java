package duke;

import duke.exceptions.InvalidCommandException;
import duke.execute.CommandExecutor;
import duke.list.TaskList;
import duke.ui.TextUi;

import java.io.IOException;

import static duke.storage.Storage.loadFile;
import static duke.storage.Storage.saveFile;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private TaskList taskList;
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

    private void start() {
        this.ui = new TextUi();
        this.taskList = new TaskList();
        ui.printInitialMsg();
        try {
            loadFile(taskList);
        } catch (IOException | SecurityException e) {
            ui.printLoadFileError();
        } catch (InvalidCommandException e) {
            ui.corruptedFileError();
        }
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        CommandExecutor executor = new CommandExecutor();
        do {
            String userCommandText = ui.scanInput();
            command = executor.executeCommand(taskList, userCommandText);
            ui.printReaction(taskList, command, userCommandText);
        } while (!ui.isExit(command));
    }

    private void exit() {
        try {
            saveFile(taskList);
        } catch (IOException | SecurityException e) {
            ui.printSaveFileError();
        }
        System.exit(0);
    }
}