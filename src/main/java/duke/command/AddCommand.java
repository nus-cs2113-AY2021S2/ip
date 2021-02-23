package duke.command;

import java.io.IOException;
import duke.Constants;
import duke.error.ImportTaskException;
import duke.error.InvalidSyntaxException;
import duke.task.*;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents the add command. An AddCommand object corresponds to the add command input by the user. 
 */
public class AddCommand extends Command {
    private String taskDescription;
    private String taskDate;

    public AddCommand(String[] commands) {
        super(commands);
        this.taskDescription = commands[Constants.TASK_DESCRIPTION_INDEX];
        this.taskDate = commands[Constants.TASK_DATE_INDEX];
    }

    /**
     * Adds a new todo task to task list. 
     * Save to storage and display success message. 
     * Applies to user input commands. 
     * 
     * @throws InvalidSyntaxException If userCommand does not contain task description.  
     */
    public void executeAddTodo(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidSyntaxException, IOException {
        if (taskDescription == null) {
            throw new InvalidSyntaxException(Constants.MESSAGE_TODO_SYNTAX);
        }
        tasks.addToTaskList(new Todo(taskDescription));
        storage.saveToFile(tasks);
        ui.displayAddTaskSuccessMessage(tasks);
    }

    /**
     * Retrieves and adds todo task from storage. 
     * 
     * @return A new instance of Todo task based on task description. 
     * @throws ImportTaskException If the task description is invalid. 
     */
    public Todo addTodoFromStorage() throws ImportTaskException {
        if (taskDescription == null) {
            throw new ImportTaskException();
        }
        return new Todo(taskDescription);
    }

    /**
     * Adds a new deadline task to task list.
     * Save to storage and display success message.
     * Applies to user input commands. 
     * 
     * @throws InvalidSyntaxException If date is not found.
     */
    public void executeAddDeadline(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidSyntaxException, IOException {
        if (taskDescription == null | taskDate == null) {
            throw new InvalidSyntaxException(Constants.MESSAGE_DEADLINE_SYNTAX);
        }
        tasks.addToTaskList(new Deadline(taskDescription, taskDate));
        storage.saveToFile(tasks);
        ui.displayAddTaskSuccessMessage(tasks);
    }

    /**
     * Retrieves and adds deadline task from storage. 
     * 
     * @return A new instance of Deadline task based on task description. 
     * @throws ImportTaskException If description or date is not found. 
     */
    public Deadline addDeadlineFromStorage() throws ImportTaskException {
        if (taskDescription == null | taskDate == null) {
            throw new ImportTaskException();
        }
        return new Deadline(taskDescription, taskDate);
    }

    /**
     * Adds a new event task to task list. 
     * Save to storage and display success message.
     * Applies to user input commands.
     * 
     * @throws InvalidSyntaxException If description or date is not found. 
     */
    public void executeAddEvent(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidSyntaxException, IOException {
        if (taskDescription == null | taskDate == null) {
            throw new InvalidSyntaxException(Constants.MESSAGE_EVENT_SYNTAX);
        }
        tasks.addToTaskList(new Event(taskDescription, taskDate));
        storage.saveToFile(tasks);
        ui.displayAddTaskSuccessMessage(tasks);
    }

    /**
     * Retrieves and adds deadline task from storage. 
     * 
     * @return A new instance of Event task based on task description. 
     * @throws ImportTaskException If description or date is not found. 
     */
    public Event addEventFromStorage() throws ImportTaskException {
        if (taskDescription == null | taskDate== null) {
            throw new ImportTaskException();
        }
        return new Event(taskDescription, taskDate);
    }
}