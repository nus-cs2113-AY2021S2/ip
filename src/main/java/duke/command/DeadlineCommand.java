package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int DEADLINE_LENGTH = 9;
    private static final int BY_LENGTH = 3;

    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = fullCommand.indexOf("/");
            String description = fullCommand.substring(DEADLINE_LENGTH, index - 1);
            String by = fullCommand.substring(index + BY_LENGTH).trim();
            Task newTask = new Deadline(description, by);
            tasks.addTask(newTask);
            ui.printNewTask(newTask, tasks.getTaskCount());
            storage.saveToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmptyDescription("deadline");
        }
    }
}
