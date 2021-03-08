package main.commands;

import main.DukeException;
import main.Event;

/**
 * Adds an event task to the task list.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class EventAddCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a event task to your task list. \n"
            + "Parameters: EVENT at/ DATE\n"
            + "Example: " + COMMAND_WORD + " Talk about Space 1 at/ Aug 6th 2-4pm";

    public final Event newEvent;

    public EventAddCommand(Event newEvent) throws DukeException {
        this.newEvent = newEvent;
    }

    @Override
    public CommandResult execute() {
        taskList.add(newEvent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newEvent), taskList);
    }
}
