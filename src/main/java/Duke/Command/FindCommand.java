package Duke.Command;

import Duke.Constant.Message;
import Duke.Constant.SpaceAndLine;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * FindCommand helps the user find task(s) containing the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    /**
     * Finds task(s) containing the keyword.
     *
     * @param tasks the list of tasks
     * @param ui outputs to be printed
     * @param storage store data into Duke.txt
     * @throws DukeException the exceptions which can happen
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, TaskList matchedTasks, TaskList sameDateTasks)
            throws DukeException {
        ui.printMessage(SpaceAndLine.SEPARATION_LINE);
        ui.printMessage(SpaceAndLine.SHORT_SPACE + Message.SHOW_MATCHING_KEYWORD_TASKS_MESSAGE);

        tasks.findKeyword(keyword);
        ui.printMatchedTasks(matchedTasks);

        ui.printMessage(SpaceAndLine.SEPARATION_LINE);
    }
}
