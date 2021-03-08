package main.commands;

import main.TaskList;

/**
 * Represents an executable command.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class Command {
    protected TaskList taskList;

    public Command() {

    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

}
