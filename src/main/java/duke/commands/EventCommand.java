package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.Event;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.commands.Utils.isArgumentValueEmpty;
import static duke.commands.Utils.parseArgument;
import static duke.common.Messages.ERROR_EMPTY_EVENT_AT_MESSAGE;
import static duke.common.Messages.ERROR_EMPTY_TASK_STRING_FORMAT;


public class EventCommand extends Command {
    public static final String EVENT_WORD = "event";
    public static final String EVENT_AT_TOKEN = "/at";

    private String commandArgs;

    public EventCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Records a new Event task into the global task array.
     * Ensures the task description and event-at is given in {@code commandArgs}
     * Fails if any argument value is invalid.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @see #validateEventArguments(String)
     */
    private void recordEvent(TaskList tasks, TextUI ui, Storage storage) {
        try {
            String[] eventArgValues = validateEventArguments(commandArgs);
            tasks.recordTask(new Event(eventArgValues[0], eventArgValues[1]), ui, storage);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    private String[] validateEventArguments(String commandArgs) throws DukeException {
        String taskDescription = parseArgument(commandArgs, null);
        String eventAt = parseArgument(commandArgs, EVENT_AT_TOKEN);
        if (isArgumentValueEmpty(taskDescription)) {
            throw new DukeException(String.format(ERROR_EMPTY_TASK_STRING_FORMAT, "event"));
        }
        if (isArgumentValueEmpty(eventAt)) {
            throw new DukeException(ERROR_EMPTY_EVENT_AT_MESSAGE);
        }
        return new String[] {taskDescription, eventAt};
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        recordEvent(tasks, ui, storage);
    }
}
