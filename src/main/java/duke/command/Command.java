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

    public void execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {

    };
}
