package commands;

import duke.IncorrectFormatException;
import duke.TodoList;

/**Default command class to be overwritten by the specific user commands*/
public class Command {

    protected TodoList inputList;
    private int separatorIndex = -1;

    public Command(int targetIndex) {
        this.separatorIndex(targetIndex);
    }
    public Command() {
    }

    private void separatorIndex(int targetIndex) {
        this.separatorIndex = targetIndex;
    }
    public void setData(TodoList input) {
        this.inputList = input;
    }

    /**Method containing instructions to execute when the command is called. Returns CommandResult to be read by Ui*/
    public CommandResult execute() throws IncorrectFormatException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };



}
