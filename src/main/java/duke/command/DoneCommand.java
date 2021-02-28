package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DoneCommand is called when user wants to mark a task as done
 */
public class DoneCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int DONE_LENGTH = 5;

    /**
     * Constructor method of DoneCommand which stores the user input
     *
     * @param fullCommand String of user input for marking a task as done
     */
    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Marks a task as done in the TaskList tasks
     * Prints output confirming operation to user
     * Saves changes to data file
     * @param tasks TaskList containing all tasks
     * @param ui User Interface
     * @param storage Storage to save all tasks, updating the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(fullCommand.substring(DONE_LENGTH)) - 1;
            tasks.getTask(index).markAsDone();
            ui.printDoneTask(tasks, index);
            storage.saveToFile(tasks);
        } catch (Exception e) {
            ui.printInvalidTask();
        }
    }
}
