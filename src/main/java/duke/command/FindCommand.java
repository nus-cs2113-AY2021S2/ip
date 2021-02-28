package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Implements find command objects.
 *
 * @author Leonardo Irvin Pratama
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses to be passed to user.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.size() == 0) {
            ui.accumulateResponses(" No tasks with matching keyword was found.");
        } else {
            ui.accumulateResponses(" Here are all the matching tasks:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.accumulateResponses("   " + (i + 1) + "." + matchingTasks.get(i) + "\n");
            }
        }
        ui.getResponses();
    }
}