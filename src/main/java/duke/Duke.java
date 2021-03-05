package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents Duke program.
 */

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Instantiates an instance of Duke.
     *
     * @param filePath file path of stored data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
    }

    /**
     * Runs the program.
     */
    public void run() throws DukeException {
        Ui.printWelcomeMessage();
        Storage.loadData();
        Parser.getInput();
    }

    /**
     * Runs Duke.
     */
    public static void main(String[] args) throws DukeException{
        new Duke("data.txt").run();
    }
}