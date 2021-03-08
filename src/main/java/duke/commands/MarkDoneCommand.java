package duke.commands;

import duke.TaskList;

import java.util.ArrayList;

/**
 * Marks a task as done, given the index of the task in the TaskList.
 */
public class MarkDoneCommand implements Command{
    ArrayList<Integer> indexes;

    public MarkDoneCommand(ArrayList<Integer> indexes) {
        this.indexes = indexes;
    }

    public void execute(TaskList tasks) {
        tasks.markTasksAsDone(indexes);

    }
}
