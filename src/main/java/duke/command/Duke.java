package duke.command;
import duke.storage.Storage;
import duke.task.*;

/**
 * Main class to be run.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeExceptions e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, storage, ui);
                isExit = c.isExit();
            } catch (DukeExceptions e) {
                ui.showError("Error");
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/duke/data/tasks.txt").run();
    }
}