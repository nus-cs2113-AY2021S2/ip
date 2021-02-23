package duke.command;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String outputText;
        outputText = Messages.MESSAGE_LIST_TASKS;
        for (Task task : tasks.getTasks()) {
            String taskNumber = (tasks.getTasks().indexOf(task) + 1) + ".";
            outputText += System.lineSeparator() +
                    " " +
                    taskNumber + " " +
                    task;
        }
        ui.printText(outputText);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
