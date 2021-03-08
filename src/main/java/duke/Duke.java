package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    public Duke() {
        ui = new TextUi();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Routine of the task manager bot. During one loop, recognizes user input and respond.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.setData(tasks, ui, storage);
                CommandResult result = c.execute();
                ui.showResultToUser(result);
                isExit = c instanceof ExitCommand;
            } catch (DukeException | IOException | IndexOutOfBoundsException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodbyeMessage();
    }
}
