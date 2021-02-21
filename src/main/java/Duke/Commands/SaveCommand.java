package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Storage;

public class SaveCommand extends Command {

    public String execute(TaskList taskList, Storage storage) {
        storage.saveData(taskList);
        return Output.printSave();
    }

}
