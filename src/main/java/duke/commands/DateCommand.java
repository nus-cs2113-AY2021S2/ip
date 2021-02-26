package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import java.time.LocalDateTime;

import static duke.commands.Utils.isArgumentValueEmpty;
import static duke.commands.Utils.parseArgument;
import static duke.common.Utils.parseDate;
import static duke.common.Messages.ERROR_MISSING_DATE_MESSAGE;
import static duke.common.Messages.ERROR_INVALID_DATE_MESSAGE;

public class DateCommand extends Command {
    public static final String DATE_WORD = "date";

    private String commandArgs;

    public DateCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    private void searchDate(TaskList tasks, TextUI ui) {
        try {
            String dateInput = validateDateArguments(commandArgs);
            LocalDateTime date = parseDate(dateInput);
            if (date != null) {
                tasks.printTasksByDate(date, ui);
            } else {
                throw new DukeException(ERROR_INVALID_DATE_MESSAGE);
            }
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    private String validateDateArguments(String commandArgs) throws DukeException {
        String dateInput = parseArgument(commandArgs, null, null);
        if (isArgumentValueEmpty(dateInput)) {
            throw new DukeException(ERROR_MISSING_DATE_MESSAGE);
        }
        return dateInput;
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        searchDate(tasks, ui);
    }
}
