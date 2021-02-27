package duke.commands;

import duke.TaskList;

public class ListCommand extends Command {

    public ListCommand(TaskList tasks) {
        super.tasks = tasks;
    }

    @Override
    public void execute() {
        ui.printList(tasks);
    }
}
