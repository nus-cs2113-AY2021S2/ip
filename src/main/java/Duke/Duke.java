package Duke;

import Duke.Command.Command;
import Duke.Constant.Message;
import Duke.Exception.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * Entry point of Duke application.
 * Initializes the application and starts interaction with the user.
 */
public class Duke {
    private static final String folderName = "data";
    private static final String fileName = "Duke.txt";

    private Storage storage;
    private TaskList tasks;
    private TaskList matchedTasks = new TaskList();
    private TaskList sameDateTasks = new TaskList();
    private Ui ui;
    private Parser parser = new Parser();

    public Duke(String folderName, String fileName) {
        ui = new Ui();
        storage = new Storage(folderName, fileName);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printMessage(Message.LOADING_ERROR_MESSAGE);
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.printMessage(Message.GREET_MESSAGE);

        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String userInput = ui.readCommand();
                Command userCommand = parser.processInput(userInput, tasks);
                userCommand.execute(tasks, ui, storage, matchedTasks, sameDateTasks);
                shouldExit = userCommand.shouldExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.printMessage(Message.EXIT_MESSAGE);
    }

    public static void main(String[] args) {
        new Duke(folderName, fileName).run();
    }
}