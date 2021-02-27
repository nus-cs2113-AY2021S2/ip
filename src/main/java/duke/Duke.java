package duke;

import duke.commands.CommandParser;
import duke.data.exceptions.InvalidCommandException;
import duke.storage.Storage;
import duke.ui.TextUI;
import duke.commands.Command;
import duke.data.task.TaskList;

import static duke.common.Messages.MESSAGE_ERROR_INVALID_COMMAND;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private TextUI ui;

    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage.retrieveTasksFromFile());
        ui = new TextUI();
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command c = CommandParser.parse(userInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                // Invalid command, reflect error to user.
                ui.printError(MESSAGE_ERROR_INVALID_COMMAND);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
