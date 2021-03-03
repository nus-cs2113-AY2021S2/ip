package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Storage;

public class ListCommand extends Command {

    /**
     * Execute the command
     * @param taskList
     * @param storage
     * @return String to be printed to the command line
     */
    public String execute(TaskList taskList, Storage storage) {
        //no other commands to execute
        return Output.printTaskList(taskList.returnTaskList());
    }

}
