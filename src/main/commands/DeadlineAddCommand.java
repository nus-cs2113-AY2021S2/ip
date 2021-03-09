package main.commands;

import main.Deadline;
import main.DukeException;

/**
 * Adds a deadline task to the task list.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class DeadlineAddCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "New Deadline added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline task to your task list. \n"
            + "Parameters: TASK by/ DATE\n"
            + "Example: " + COMMAND_WORD + " Math Homework 1 by/ Tues 2pm";

    public final Deadline newDeadline;

    public DeadlineAddCommand(Deadline newDeadline) throws DukeException {
        this.newDeadline = newDeadline;
    }

    @Override
    public CommandResult execute() {
        taskList.add(newDeadline);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newDeadline), taskList);
    }
}
