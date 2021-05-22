package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Storage;

public class ExitCommand extends Command{

    /**
     * Sets isExit to true
     */
    public ExitCommand() {
        this.setExit();
    }

    /**
     * Execute the command
     * @param taskList
     * @param storage
     * @return String to be printed to the command line
     */
    public String execute(TaskList taskList, Storage storage) {
        return Output.printExit();
    }

}
