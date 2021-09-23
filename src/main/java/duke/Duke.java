package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements 9% chatbot command line interface
 *
 * @author Leonardo Irvin Pratama
 */
public class Duke {
    private static final String SEPARATOR = System.getProperty("line.separator");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a Duke object
     **/
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException error) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts Duke.
     *
     * @param args Command line argument.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.getGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.executeCommand(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
