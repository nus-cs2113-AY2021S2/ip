import command.Command;
import exception.DukeException;
import parser.Parser;
import task.TaskManager;
import ui.Ui;

import java.io.FileNotFoundException;


public class Duke {
    private Ui ui;
    private TaskManager tasksList;

    public Duke() throws FileNotFoundException, DukeException {
        ui = new Ui();
        tasksList = new TaskManager();
    }

    /***
     * Runs the duke program
     */
    public void run() {
        boolean isBye = false;
        ui.printMenu();
        do {
            try {
                String userInput = ui.getUserInput();
                String newUserInput = userInput.toUpperCase();
                Command command = Parser.parseCommand(newUserInput.toUpperCase());
                command.executeCommand(newUserInput, tasksList);
                isBye = ui.sayGoodBye(newUserInput);
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        } while (!isBye);
    }

    public static void main(String[] args) throws FileNotFoundException, DukeException {
        new Duke().run();
    }
}

