package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.Event;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.commands.Utils.isArgumentValueEmpty;
import static duke.commands.Utils.parseArgument;
import static duke.common.Messages.MESSAGE_ERROR_EMPTY_EVENT_AT;
import static duke.common.Messages.SF_ERROR_EMPTY_DESCRIPTION;


public class EventCommand extends Command {
    public static final String EVENT_WORD = "event";
    public static final String EVENT_AT_TOKEN = "/at";
    private static final int DESCRIPTION_INDEX = 0;
    private static final int BY_INDEX = 1;

    private String commandArgs;

    public EventCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Records a new Deadline task into the TaskList object.
     * Ensures the task description and event-at is in the commandArgs string.
     * Fails if any argument value is invalid and an error message is printed.
     *
     * @param tasks the TaskList object that contains the list of tasks.
     * @param ui the TextUI object that that engages user input and program output.
     * @param storage the Storage object that writes/retrieves to/from file.
     * @see #validateEventArguments(String)
     */
    private void recordEvent(TaskList tasks, TextUI ui, Storage storage) {
        try {
            String[] eventArgValues = validateEventArguments(commandArgs);
            Event event = new Event(
                    eventArgValues[DESCRIPTION_INDEX],
                    eventArgValues[BY_INDEX]);
            tasks.recordTask(event, ui, storage);
        } catch (DukeException e) {
            // An argument value is missing, reflect error to user.
            ui.printError(e.getMessage());
        }
    }

    /**
     * Validates each argument for the task description and event-at.
     * Returns the extracted argument values in an array.
     * Throws an exception if there are missing argument values.
     *
     * @param commandArgs a string containing the command's arguments.
     * @return an array of size 2; first element is the task description
     *         and second element is the event-at string.
     * @throws DukeException If any of the arguments is empty.
     */
    private String[] validateEventArguments(String commandArgs) throws DukeException {
        String taskDescription = parseArgument(commandArgs, null, EVENT_AT_TOKEN);
        String eventAt = parseArgument(commandArgs, EVENT_AT_TOKEN, null);
        if (isArgumentValueEmpty(taskDescription)) {
            throw new DukeException(String.format(SF_ERROR_EMPTY_DESCRIPTION, "event"));
        }
        if (isArgumentValueEmpty(eventAt)) {
            throw new DukeException(MESSAGE_ERROR_EMPTY_EVENT_AT);
        }
        return new String[] {taskDescription, eventAt};
    }

    @Override
    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        recordEvent(tasks, ui, storage);
    }
}
