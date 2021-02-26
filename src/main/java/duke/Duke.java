package duke;

import duke.command.Command;
import duke.command.CommandResult;

import java.io.FileNotFoundException;

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
    private Ui ui;
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
            storage.createDirectory();
            tasks = new TaskList();
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
                isExit = command.isExit();
            } catch (Exception exception) {
                ui.showErrorMessage();
            }
        }
        ui.showExitMessage();
        storage.save(tasks);
        ui.showSavedMessage();
    }
}
