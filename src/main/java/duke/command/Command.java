package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskManager;


public class Command {

    protected String userInput;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public Command(String s) {
        userInput = s;
    }

    /**
     * Executes a command according to the user input
     * @param tasks the arraylist of tasks currently stored
     * @param ui show messages to interact with users
     * @param storage update the file once the task lists changed
     * @throws DukeException if the command user input is in wrong structure.
     */
    public void execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {

    };
}
