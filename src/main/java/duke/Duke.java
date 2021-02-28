package duke;

import duke.Commands.Command;
import duke.Exceptions.DukeException;

/**
 * The Duke Personal Assistant
 */
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Initialises the UI, TaskList and Storage
     * Loads the data from file specified in <code>filePath</code>
     * If file at <code>filePath</code> does not exist, it initialises a new <code>TaskList</code>
     * @param filePath path of file to load and save data to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Reads a user command and parses out the intended command
     * Executes the commands until <code>ExitCommand</code> is given
     * Shows error message when exception occurs
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs the Duke program
     * @param args initial input arguments
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
