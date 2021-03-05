public class FindCommand extends Command{
    public String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        assert keyword != null;
        inputTasks.findKeyword(keyword, ui);
    }
}