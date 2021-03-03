package Duke.Commands;

import Duke.Output;
import Duke.Storage;
import Duke.TaskList;

public class FindCommand extends Command {

    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList taskList, Storage storage) {
        return Output.printFilteredTaskList(taskList.returnFilteredTaskList(keyword));
    }

}
