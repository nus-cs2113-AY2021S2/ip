package commands;

import io.DukePrint;
import models.TaskList;

public class DoneCommand extends Command {

    /**
     * Index of the Task to be marked as done as specified by User
     */
    private final int taskCount;
    private TaskList taskList;


    public DoneCommand(TaskList taskList, int taskCount, DukePrint dukePrint) {
        super(dukePrint);
        this.taskList = taskList;
        this.taskCount = taskCount;
    }

    /**
     * Marks the specific Task as done and prints the done message
     */
    @Override
    public void execute() {
        taskList.get(taskCount - 1).markAsDone();
        dukePrint.printDone(taskList.get(taskCount - 1));
    }
}
