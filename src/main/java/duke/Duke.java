package duke;

import java.io.IOException;
import java.util.List;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Sets up the required objects, loads up the data from the storage file.
     *
     * @param filePath Location to load and save the data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();

    }

    /**
     * Prints the welcome message, runs the program until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showLine();
                List<String> commandStr = Parser.parse(fullCommand);
                isExit = tasks.process(commandStr, isExit);
            } finally {
                Ui.showLine();
            }
        }
    }
}
