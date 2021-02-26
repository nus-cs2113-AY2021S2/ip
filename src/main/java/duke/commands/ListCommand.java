package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.common.Messages.LIST_NO_TASK_MESSAGE;

public class ListCommand extends Command {
    public static final String LIST_WORD = "list";

    /**
     * Lists all tasks in the TaskList object.
     *
     * @param tasks the TaskList object that contains the list of tasks.
     * @param ui the TextUI object that that engages user input and program output.
     */
    private void listTasks(TaskList tasks, TextUI ui) {
        if (tasks.isEmpty()) {
            ui.printStatements(LIST_NO_TASK_MESSAGE);
        } else {
            ui.printHorizontalLine();
            tasks.printAllTasks(ui);
            ui.printHorizontalLine();
        }
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        listTasks(tasks, ui);
    }
}
