package duke.command;

import duke.TaskList;
import duke.Ui;

import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final String LIST_ITEMS_MESSAGE = "Eez are the tings you added to the list";

    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    public ListCommand(String input, TaskList taskList, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute(Ui ui) {
        ArrayList<Task> tasks = this.taskList.getTasks();
        listItems(tasks);
    }

    /** Lists all items that were added to the list. */
    public void listItems(ArrayList<Task> tasks) {
        System.out.println(LIST_ITEMS_MESSAGE);
        for(int i = 0; i < Task.totalNumberOfTasks; i++) {
            this.ui.printListItem(i+1, tasks.get(i).getType(), tasks.get(i).getStatusIcon(),
                    tasks.get(i).getName(), tasks.get(i).getDate());
        }
        this.ui.printBorderLine();
    }
}
