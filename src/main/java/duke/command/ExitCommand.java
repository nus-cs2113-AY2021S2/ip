package duke.command;

import duke.storage.Storage;
import duke.task.*;

/**
 * Represents the executor to exit program
 */
public class ExitCommand extends Command {

    /**
     * Constructor of this class. Sets exit to be true to exit program.
     */
    public ExitCommand() {
        this.exit = true;
    }

    /**
     * Executor to exit program
     * @param taskList TaskList object that stores all current tasks
     * @param storage Storage object that communicates with database
     * @param ui Ui object that handles the interaction with user
     * @throws DukeExceptions does not occur
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions {
        System.out.println("bye");
    }

    /**
     * Checks if we want to exit the program
     * @return whether or not to exit the program
     */
    @Override
    public boolean isExit() {
        return exit;
    }
}
