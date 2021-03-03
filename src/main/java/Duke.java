import command.Command;
import exception.DukeException;
import parser.Parser;
import task.TaskManager;
import ui.Ui;

import java.io.FileNotFoundException;



public class Duke {
    private Ui ui;
    private TaskManager taskList;

    public Duke() throws FileNotFoundException, DukeException {
        ui = new Ui();
        taskList = new TaskManager();
    }

    /***
     * Runs the duke program
     */
    public void run() {
        boolean isBye;
        ui.printMenu();
        do {
            String userInput = ui.getUserInput();
            String newUserInput = userInput.toUpperCase();
            Command command = Parser.parseCommand(newUserInput.toUpperCase());
            command.executeCommand(newUserInput, taskList);
            isBye = ui.sayGoodBye(newUserInput);
        } while (!isBye);
    }

    public static void main(String[] args) throws FileNotFoundException, DukeException {
            new Duke().run();
        }
}

