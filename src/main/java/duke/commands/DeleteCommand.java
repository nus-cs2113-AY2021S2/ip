package duke.commands;

import duke.TaskList;

import java.util.ArrayList;
import java.util.Collections;

public class DeleteCommand implements Command {
    ArrayList<Integer> indexes;

    public DeleteCommand(ArrayList<Integer> indexes) {
        this.indexes = indexes;
    }

    public void execute(TaskList tasks) {
        //sort indexes in descending order so deletion will not affect index
        Collections.sort(indexes, Collections.reverseOrder());
        tasks.deleteTasks(indexes);
    }
}
