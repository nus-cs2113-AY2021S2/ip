package duke.commands;

import duke.TaskList;

import java.util.ArrayList;

public class MarkDoneCommand implements Command{
    ArrayList<Integer> indexes;

    public MarkDoneCommand(ArrayList<Integer> indexes) {
        this.indexes = indexes;
    }

    public void execute(TaskList tasks) {
        tasks.markTasksAsDone(indexes);

    }
}
