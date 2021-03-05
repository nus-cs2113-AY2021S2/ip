package duke.app;

import duke.commands.Command;
import duke.parser.Parser;
import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

/*
Main Application of Duke
*/
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /*
    Constructor of Duke
    Initialize Storage and Tasklist Object
    Load data from duke.txt into Tasklist Object
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /*
    Main logic of the Application
    Run the tasks command by users
    Keeps running until user enters "bye"
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /*
    Create Duke Object by passing the file to be read
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
