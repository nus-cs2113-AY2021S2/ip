package main.commands;

import main.DukeException;
import main.Todo;

/**
 * Adds a todo task to the task list.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class TodoAddCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_SUCCESS = "New Todo added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to your task list. \n"
            + "Example: " + COMMAND_WORD + " read book";

    public final Todo newTodo;

    public TodoAddCommand(Todo newTodo) throws DukeException {
        this.newTodo = newTodo;
    }

    @Override
    public CommandResult execute(){
        taskList.add(newTodo);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTodo),taskList);
    }
}
