package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Storage;

public class ExitCommand extends Command{

    public ExitCommand() {
        this.setExit();
    }

    public String execute(TaskList taskList, Storage storage) {
        return Output.printExit();
    }

}
