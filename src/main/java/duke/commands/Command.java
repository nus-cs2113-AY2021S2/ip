package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.IOException;

import static duke.ui.TextUi.DISPLAYED_INDEX_OFFSET;

/**
 * Represents an executable command.
 */
public class Command {
    protected TaskList tasks;
    protected TextUi ui;
    protected Storage storage;
    private int targetIndex = -1;

    /**
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    private void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex - DISPLAYED_INDEX_OFFSET;
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() throws DukeException, IOException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TaskList tasks, TextUi ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return (Task) tasks.getTaskList().get(getTargetIndex());
    }

    public int getTargetIndex() {
        return targetIndex;
    }
}
