package duke.commands;

import static duke.Ui.PRINT_FULL_LIST_STATEMENT;

import duke.TaskList;

public class ListCommand extends Command {

    public ListCommand(TaskList tasks) {
        super.tasks = tasks;
    }

    @Override
    public void execute() {
        ui.printList(tasks, PRINT_FULL_LIST_STATEMENT);
    }
}
