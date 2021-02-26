package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int DONE_LENGTH = 5;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

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
