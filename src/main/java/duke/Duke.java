package duke;

import duke.commands.Command;

/**
 * Starts the Duke application and begins interactions with user.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initialises required objects and loads any existing data from the storage file.
     *
     * @param filePath pre-defined path to the file where data is loaded from and saved to
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.loadFromDisk();
        ui = new Ui();
        parser = new Parser(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the application until termination.
     */
    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            Command userCommand = parser.processInput(userInput);
            userCommand.execute();
            isExit = userCommand.isExit();
        }
        storage.saveToDisk(tasks);
    }
}
