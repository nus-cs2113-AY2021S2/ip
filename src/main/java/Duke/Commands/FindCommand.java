package Duke.Commands;

import Duke.Output;
import Duke.Storage;
import Duke.TaskList;

public class FindCommand extends Command {

    /**
     * Keyword for querying by the user
     */
    String keyword;

    /**
     * Constructor
     * @param keyword by the user to be set
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the command
     * @param taskList
     * @param storage
     * @return String to be printed to the command line
     */
    public String execute(TaskList taskList, Storage storage) {
        return Output.printFilteredTaskList(taskList.returnFilteredTaskList(keyword));
    }

}
