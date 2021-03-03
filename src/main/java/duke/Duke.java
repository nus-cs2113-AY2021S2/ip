package duke;


import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;



public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke Class.
     * Initialises UI, Storage and TaskList.
     */
    public Duke() {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.showError(e.getErrorMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     * Reads in user input, parses input into commands and executes them.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String line = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(line);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getErrorMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs an instance of Duke.
     *
     * @param args arguments from command line (if any)
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
