package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.File;

import duke.Command.Command;

public class Duke {

    private TaskList tasks;
    private final Ui ui = new Ui();
    private final String home = System.getProperty("user.home");
    private final Path filePath = Paths.get(home, "Documents","duke.txt");
    private final Storage storage = new Storage(filePath);

    public Duke() {
        try {
            ui.showLoading();
            tasks = storage.loadFile();
            ui.showLoadingSuccess();
        } catch (DukeException e) {
            ui.showLoadingError(new File(filePath.toString()));
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showSeparator();
                Parser parser = new Parser();
                Command c =  parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showSeparator();
            }
        }
        try {
            storage.writeToFile(tasks);
            ui.showSavingSuccess(new File(filePath.toString()));
        } catch (IOException e) {
            ui.showError(e);
        }
        ui.showSeparator();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
