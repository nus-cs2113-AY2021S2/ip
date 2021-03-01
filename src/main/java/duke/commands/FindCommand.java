package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String searchWord;

    public FindCommand(TaskList taskList, String searchWord) {
        super(taskList); 
        this.searchWord = searchWord;
    }

    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.findTask(searchWord);
    }
}
