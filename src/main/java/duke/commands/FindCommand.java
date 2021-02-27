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

    /**
     * Finds matches of the keyword in TaskList object and prints
     * them out.
     * Fails if keyword is an empty string.
     *
     * @param tasks the TaskList object that contains the list of tasks.
     * @param ui the TextUI object that that engages user input and program output.
     * @see #validateFindKeywordArguments(String)
     * @see TaskList#findKeyword(String, TextUI)
     */
    private void findKeyword(TaskList tasks, TextUI ui) {
        try {
            String keyword = validateFindKeywordArguments(commandArgs).toLowerCase();
            tasks.findKeyword(keyword, ui);
        } catch (DukeException e) {
            // keyword is a empty string, reflect error to user.
            ui.printError(e.getMessage());
        }
    }

    /**
     * Extracts the keyword value and validates if it is not empty.
     * Returns the string containing the keyword.
     * Throws an exception if the argument value is missing.
     *
     * @param commandArgs a string containing the command's arguments.
     * @return a string containing the keyword parsed from the commandArgs.
     * @throws DukeException If the argument is empty.
     */
    private String validateFindKeywordArguments(String commandArgs) throws DukeException {
        String keyword = parseArgument(commandArgs, null, null);
        if (isArgumentValueEmpty(keyword)) {
            throw new DukeException(ERROR_MISSING_KEYWORD_MESSAGE);
        }
        return keyword;
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        findKeyword(tasks, ui);
    }
}
