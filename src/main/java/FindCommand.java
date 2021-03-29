/**
 * find a task by keyword
 */
public class FindCommand extends Command{
    public String keyword;

    /**
     * FindCommand Constructor
     * @param keyword the keyword to be searched
     */
    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    /**
     * execute of FindCommand
     * @param inputTasks list of tasks
     * @param storage storage used
     * @param ui ui used
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        assert keyword != null;
        inputTasks.findKeyword(keyword, ui);
    }
}