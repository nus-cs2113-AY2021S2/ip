package duke;

import duke.command.Command;
import duke.command.CommandResult;

import duke.exception.EmptyStringException;

import java.io.FileNotFoundException;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private static final String FILE_PATH = "src/data/tasks.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadSuccess();
        } catch (FileNotFoundException fileNotFoundException) {
            ui.showLoadError();
            storage.createDirectory();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getUserCommand();
                Command command = new Parser().parseInput(userInput, tasks);
                command.setTasks(tasks);
                CommandResult result = command.execute();
                ui.showMessage(result.messageToUser);
                isExit = command.isExit();
            } catch (EmptyStringException emptyStringException) {
                ui.showErrorMessage(emptyStringException);
            }
        }
        ui.showExitMessage();
        storage.save(tasks);
        ui.showSavedMessage();
    }
}
