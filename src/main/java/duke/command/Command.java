package duke.command;

import duke.error.*;

import java.io.IOException;

import duke.Constants;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Command {
    // If done and delete commands, taskDescription contains task number
    // Otherwise, taskDescription contains task name
    protected String[] commands;
    private String command;

    public Command(String[] commands) {
        this.commands = commands;
        this.command = commands[Constants.COMMAND_INDEX];
    }

    public String getCommand(int index) {
        return command;
    }

    /**
     * Executes each function according to command word given.
     * 
     * The following explains the behaviour of the command words: 
     * COMMAND_EXIT_WORD:
     * - Displays an exit message. 
     * - Exits program. 
     * COMMAND_LIST_WORD: 
     * - Lists all tasks in tasks list. 
     * COMMAND_MARK_WORD: 
     * - Obtains task number. 
     * - Checks if task is valid. 
     * - If valid, mark task as done then display success message. 
     * - Otherwise, display error message. 
     * COMMAND_TODO_WORD: 
     * - Add new todo task to list. 
     * COMMAND_DEADLINE_WORD: 
     * - Add new deadline task to list.
     * COMMAND_EVENT_WORD: 
     * - Add new event task to list. 
     * COMMAND_DELETE_WORD:
     * - Delete a given task from the list. 
     * - Add new event task to list. 
     * NO_COMMAND: 
     * - No command detected, display error message. 
     * DEFAULT: 
     * - Invalid command, display error message.
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws IllegalCommandException, IndexOutOfBoundsException, TaskListEmptyException, IllegalArgumentException, IOException, NullPointerException, InvalidSyntaxException {
        switch (command) {
        case Constants.COMMAND_EXIT_WORD:
            // Fallthrough
            ui.displayToUser(Constants.MESSAGE_EXIT);
            ui.displayMessageBorder();
            ExitCommand.executeExitProgramRequest();
        case Constants.COMMAND_LIST_WORD:
            ListCommand.executeListAllTasks(tasks, ui);
            return;
        case Constants.COMMAND_MARK_WORD:
            MarkCommand markCommand = new MarkCommand(commands);
            markCommand.executeMarkTask(tasks);
            ui.displayMarkTaskSuccessMessage(tasks, markCommand.getTaskNumber());
            storage.saveToFile(tasks);
            return;
        case Constants.COMMAND_TODO_WORD:
            AddCommand addCommandTodo = new AddCommand(commands);
            tasks.addToTaskList(addCommandTodo.executeAddTodo());
            storage.saveToFile(tasks);
            ui.displayAddTaskSuccessMessage(tasks);
            return;
        case Constants.COMMAND_DEADLINE_WORD:
            AddCommand addCommandDeadline = new AddCommand(commands);
            tasks.addToTaskList(addCommandDeadline.executeAddDeadline());
            storage.saveToFile(tasks);
            ui.displayAddTaskSuccessMessage(tasks);
            return;
        case Constants.COMMAND_EVENT_WORD:
            AddCommand addCommandEvent = new AddCommand(commands);
            tasks.addToTaskList(addCommandEvent.executeAddEvent());
            storage.saveToFile(tasks);
            ui.displayAddTaskSuccessMessage(tasks);
            return;
        case Constants.COMMAND_DELETE_WORD:
            DeleteCommand deleteCommand = new DeleteCommand(commands);
            String deletedTask = deleteCommand.executeDeleteTask(tasks);
            storage.saveToFile(tasks);
            ui.displayDeleteTaskSuccessMessage(deletedTask);
            return;
        case Constants.COMMAND_FIND_WORD:
            FindCommand findCommand = new FindCommand(commands);
            findCommand.executeFindTask(tasks, ui);
            return;
        default:
            throw new IllegalCommandException();
        }
    }
}
