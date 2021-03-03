package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Storage;

public class ListCommand extends Command {

    public String execute(TaskList taskList, Storage storage) {
        //no other commands to execute
        return Output.printTaskList(taskList.returnTaskList());
    }

}
