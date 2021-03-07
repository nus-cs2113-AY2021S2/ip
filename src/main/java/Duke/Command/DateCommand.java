package Duke.Command;

import Duke.Constant.Message;
import Duke.Constant.SpaceAndLine;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

import java.time.LocalDate;

/**
 * DateCommand helps the user finds a task which occur on the date specified.
 */
public class DateCommand extends Command {
    private final String dateString;
    private LocalDate date;

    public DateCommand(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    /**
     * Finds a task which occur on the date specified and print it out to the user
     *
     * @param tasks the list of tasks
     * @param ui do outputs
     * @param storage store the data
     * @param matchedTasks the list of matched tasks
     * @param sameDateTasks the list of tasks occurring on the same specified date
     * @throws DukeException the exceptions which can happen during add command execution
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, TaskList matchedTasks, TaskList sameDateTasks)
            throws DukeException {
        ui.printMessage(SpaceAndLine.SEPARATION_LINE);
        ui.printMessage(SpaceAndLine.SHORT_SPACE + Message.SHOW_SAME_DATE_TASKS_MESSAGE);

        date = LocalDate.parse(dateString);
        tasks.findDate(date);

        ui.printSameDateTasks(sameDateTasks);
        ui.printMessage(SpaceAndLine.SEPARATION_LINE);
    }
}
