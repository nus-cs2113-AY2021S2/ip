package command;

import exception.DukeException;
import task.TaskManager;
import ui.Ui;

public class HelpCommand extends Command {
    private Ui ui;

    @Override
    public void executeCommand(String userInput, TaskManager tasksList) throws DukeException {
        ui.printMenu();
    }
}
