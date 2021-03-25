package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.InvalidFileFormatException;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <h1>Duke</h1>
 * The Duke program implements an application that
 * interact with users to keep track of their daily
 * task.
 *
 * @author Sim Jing Jie
 * @version 0.2
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Create the required objects and load previous saved data
     * from specify file path.
     *
     * @param filePath the file path specify by the user
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadSuccess();
        } catch (FileNotFoundException fileNotFoundException) {
            ui.showLoadError();
            try {
                storage.createDirectory();
                ui.showCreateDirectorySuccess();
            } catch (IOException ioException) {
                ui.showFailToCreateDirectory();
            }
            tasks = new TaskList();
        } catch (InvalidFileFormatException invalidFileFormatException) {
            tasks = new TaskList();
            ui.showExceptionMessage(invalidFileFormatException);
        }
     }

    public static void main(String[] args) {
        new Duke("src/data/tasks.txt").run();
    }

    /**
     * Reads user command and execute it until user initiate an exit command.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command command = new Parser().parseInput(userInput, tasks);
                command.setTasks(tasks);
                CommandResult result = command.execute();
                ui.showMessage(result.messageToUser);
                storage.save(tasks);
                isExit = command.isExit();
            } catch (Exception exception) {
                ui.showExceptionMessage(exception);
            }
        }
        ui.showExitMessage();
    }
}
