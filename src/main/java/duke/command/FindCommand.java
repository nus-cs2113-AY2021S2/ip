package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int FIND_LENGTH = 5;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String query = fullCommand.substring(FIND_LENGTH);
            ui.printQueryList(tasks.getTasksByKeyword(query));
        } catch (Exception e) {
            ui.printEmptyQuery();
        }
    }
}