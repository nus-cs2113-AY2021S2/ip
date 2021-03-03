package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Storage;

public class SaveCommand extends Command {

    /**
     * Execute the command
     * @param taskList
     * @param storage
     * @return String to be printed to the command line
     */
    public String execute(TaskList taskList, Storage storage) {
        storage.saveData(taskList);
        return Output.printSave();
    }

}
