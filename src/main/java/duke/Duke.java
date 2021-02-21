package duke;


import duke.command.Command;
import duke.exception.InvalidCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

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
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandMessage();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
