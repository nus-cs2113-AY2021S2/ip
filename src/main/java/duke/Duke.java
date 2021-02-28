package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is a command line task manager
 * It allows users to add, delete, save, and complete to-dos, deadlines, and events
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        tasks = new TaskList(storage.loadFile());
    }

    /**
     * The code to run the Duke program
     * Takes in user input and executes command's accordingly
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
