package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import java.time.LocalDateTime;

import static duke.commands.Utils.isArgumentValueEmpty;
import static duke.commands.Utils.parseArgument;
import static duke.common.Utils.parseDate;
import static duke.common.Messages.MESSAGE_ERROR_MISSING_DATE;
import static duke.common.Messages.MESSAGE_ERROR_INVALID_DATE;

public class DateCommand extends Command {
    public static final String DATE_WORD = "date";

    private String commandArgs;

    public DateCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Searches for the matching tasks date in TaskList object and prints
     * them out.
     * Fails if dateInput is not a parsable into a LocalDateTime.
     *
     * @param tasks the TaskList object that contains the list of tasks.
     * @param ui the TextUI object that that engages user input and program output.
     * @see #validateDateArguments(String)
     * @see TaskList#printTasksByDate(LocalDateTime, TextUI)
     */
    private void searchDate(TaskList tasks, TextUI ui) {
        try {
            String dateInput = validateDateArguments(commandArgs);
            LocalDateTime date = parseDate(dateInput);
            if (date != null) {
                tasks.printTasksByDate(date, ui);
            } else {
                throw new DukeException(MESSAGE_ERROR_INVALID_DATE);
            }
        } catch (DukeException e) {
            // dateInput is not a parsable, reflect error to user.
            ui.printError(e.getMessage());
        }
    }

    /**
     * Extracts the dateInput value and validates if it is not empty.
     * Returns the string containing the dateInput.
     * Throws an exception if the argument value is missing.
     *
     * @param commandArgs a string containing the command's arguments.
     * @return a string containing the dateInput parsed from the commandArgs.
     * @throws DukeException If the argument is empty.
     */
    private String validateDateArguments(String commandArgs) throws DukeException {
        String dateInput = parseArgument(commandArgs, null, null);
        if (isArgumentValueEmpty(dateInput)) {
            throw new DukeException(MESSAGE_ERROR_MISSING_DATE);
        }
        return dateInput;
    }

    @Override
    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        searchDate(tasks, ui);
    }
}
