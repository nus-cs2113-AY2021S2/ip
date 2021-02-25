package duke.command;

import duke.common.Messages;
import duke.common.Utils;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Command to find tasks by date
 */
public class DateCommand extends Command {
    /**
     * Constructor for DateCommand. Takes in command arguments, sets the command type and arguments.
     * @param commandArgs command arguments for user input
     */
    public DateCommand(String commandArgs) {
        super(CommandType.DATE, commandArgs);
    }

    /**
     * Handles finding tasks by date and printing output information.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        LocalDateTime date = Utils.getDateFromUserInput(commandArgs);

        ArrayList<Task> matches = tasks.findTasksByDate(date);
        if (matches.size() == 0) {
            ui.printText(Messages.INFO_DATES_NOT_FOUND + commandArgs);
            return;
        }

        ui.printText("Here are deadlines/events on " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ":");
        matches.stream()
                .forEach(m -> ui.printText(" " + tasks.getTaskNumber(m) + ". " + m));
    }

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
