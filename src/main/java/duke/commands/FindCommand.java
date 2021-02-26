package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.commands.Utils.isArgumentValueEmpty;
import static duke.commands.Utils.parseArgument;
import static duke.common.Messages.ERROR_MISSING_KEYWORD_MESSAGE;

public class FindCommand extends Command {
    public static final String FIND_WORD = "find";

    private String commandArgs;

    public FindCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    private void findKeyword(TaskList tasks, TextUI ui, Storage storage) {
        try {
            String keyword = validateFindKeywordArguments(commandArgs).toLowerCase();
            tasks.findKeyword(keyword, ui);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    private String validateFindKeywordArguments(String commandArgs) throws DukeException {
        String keyword = parseArgument(commandArgs, null, null);
        if (isArgumentValueEmpty(keyword)) {
            throw new DukeException(ERROR_MISSING_KEYWORD_MESSAGE);
        }
        return keyword;
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        findKeyword(tasks, ui, storage);
    }
}
