package Duke.Commands;

import Duke.Exceptions.RangeError;
import Duke.TaskList;
import Duke.Storage;

/**
 * Abstract Command class to be extended by all other command classes
 */
abstract public class Command {

    /**
     * Variable to be set as true when the Exit command is called
     */
    private boolean isExit = false;

    /**
     * Execute the command
     * @param tasks
     * @param storage
     * @return String to be printed to the command line
     * @throws RangeError
     */
    public abstract String execute(TaskList tasks, Storage storage) throws RangeError;

    /**
     * Method to check if isExit is set as true
     * @return
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets isExit to true
     */
    public void setExit() {
        this.isExit = true;
    }


}
