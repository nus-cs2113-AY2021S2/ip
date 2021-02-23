package duke.command;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class FindCommand extends Command {
    public FindCommand(String commandArgs) {
        super(CommandType.FIND, commandArgs);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matches = (ArrayList<Task>) tasks.getTasks().stream()
                .filter((t) -> t.getDescription().contains(commandArgs))
                .collect(toList());
        if (matches.size() == 0) {
            ui.printText(Messages.MESSAGE_NOT_FOUND_TASKS + commandArgs);
            return;
        }
        ui.printText(Messages.MESSAGE_FOUND_TASKS);
        matches.stream()
                .forEach((m) -> ui.printText(" " + tasks.getTaskNumber(m) + ". " + m));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
