package duke;

import duke.command.Command;

import java.io.File;
import java.nio.file.Paths;

/**
 * Duke program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates Storage, TaskList and Ui components of the program
     *
     * @param filePath
     */
    public Duke(File filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException de) {
            ui.showErrorMessage(de);
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke program
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException de) {
                ui.showErrorMessage(de);
            } finally {
                ui.showDivider();
            }
        }
    }

    public static void main(String[] args) {
        File filePath = Paths.get("data/tasks.txt").toFile();
        new Duke(filePath).run();
    }

}
