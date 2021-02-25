package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.commands.Utils.parseNumberFromArgument;
import static duke.common.Messages.ERROR_NOT_A_TASK_NUMBER_MESSAGE;

public class DoneCommand extends Command {
    public static final String DONE_WORD = "done";

    private String commandArgs;

    public DoneCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    private void markTaskDone(TaskList tasks, TextUI ui, Storage storage) {
        try {
            int taskNumber = parseNumberFromArgument(commandArgs);
            tasks.markTaskDone(taskNumber, ui, storage);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printError(ERROR_NOT_A_TASK_NUMBER_MESSAGE);
        }
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        markTaskDone(tasks, ui, storage);
    }
}
