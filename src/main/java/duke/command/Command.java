package duke.command;

import java.io.IOException;
import duke.error.*;
import duke.Constants;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command input by user. A Command object corresponds to a command input by the user. 
 */
public class Command {
    protected String[] commands;
    private String command;

    /**
     * Constructor for commands used in retrieving storage data. Only command is stored in parent class. 
     * The array is inherited by child classes. 
     * 
     * @param commands An array of size 4 containing command, task status, task description and task date. 
     *                 For todo tasks, task date is null. 
     */
    public Command(String[] commands) {
        this.commands = commands;
        this.command = commands[Constants.COMMAND_INDEX];
    }

    /**
     * Retrieves the stored command. 
     * 
     * @return command. 
     */
    public String getCommand() {
        return command;
    }

    /**
     * Executes each function according to command word given.
     * 
     * The following explains the behaviour of the command words: 
     * COMMAND_EXIT_WORD:
     * - Displays exit message and exits the program. 
     * COMMAND_LIST_WORD:
     * - Displays all tasks in the tasklist. 
     * COMMAND_MARK_WORD:
     * - Marks a specified task (identified with task number) as done (i.e. true). 
     * COMMAND_TODO_WORD:
     * - Creates a new todo task and add to tasklist. 
     * COMMAND_DEADLINE_WORD:
     * - Creates a new deadline task and add to tasklist. 
     * COMMAND_EVENT_WORD:
     * - Creates a new event task and add to tasklist. 
     * COMMAND_DELETE_WORD:
     * - Deletes a specified task (identified with task number). 
     * COMMAND_FIND_WORD:
     * - Finds and display all tasks that matches the keyword provided. 
     * DEFAULT: 
     * - Throw IllegalCommandException. 
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) 
            throws IllegalCommandException, IndexOutOfBoundsException, 
            TaskListEmptyException, IllegalArgumentException, IOException, 
            InvalidSyntaxException {
        switch (command) {
        case Constants.COMMAND_EXIT_WORD:
            // Fallthrough
            ExitCommand.executeExitProgramRequest(ui);
        case Constants.COMMAND_LIST_WORD:
            ListCommand.executeListAllTasks(tasks, ui);
            return;
        case Constants.COMMAND_MARK_WORD:
            MarkCommand markCommand = new MarkCommand(commands);
            markCommand.executeMarkTask(tasks, ui, storage);
            return;
        case Constants.COMMAND_TODO_WORD:
            AddCommand addCommandTodo = new AddCommand(commands);
            addCommandTodo.executeAddTodo(tasks, ui, storage);
            return;
        case Constants.COMMAND_DEADLINE_WORD:
            AddCommand addCommandDeadline = new AddCommand(commands);
            addCommandDeadline.executeAddDeadline(tasks, ui, storage);
            return;
        case Constants.COMMAND_EVENT_WORD:
            AddCommand addCommandEvent = new AddCommand(commands);
            addCommandEvent.executeAddEvent(tasks, ui, storage);
            return;
        case Constants.COMMAND_DELETE_WORD:
            DeleteCommand deleteCommand = new DeleteCommand(commands);
            deleteCommand.executeDeleteTask(tasks, ui, storage);
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
