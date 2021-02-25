package commands;

import io.DukePrint;
import models.TaskList;

public class DoneCommand extends Command {

    private TaskList taskList;
    private int taskCount;


    public DoneCommand(TaskList taskList, int taskCount, DukePrint dukePrint) {
        super(dukePrint);
        this.taskList = taskList;
        this.taskCount = taskCount;
    }

    @Override
    public void execute() {
        taskList.get(taskCount - 1).markAsDone();
        dukePrint.printDone(taskList.get(taskCount - 1));
    }
}
