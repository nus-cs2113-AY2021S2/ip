package duke.command;

import duke.Constants;
import duke.error.ImportTaskException;
import duke.error.InvalidSyntaxException;
import duke.task.*;

public class AddCommand extends Command {
    private String taskDescription;
    private String taskDate;

    public AddCommand(String[] commands) {
        super(commands);
        this.taskDescription = commands[Constants.TASK_DESCRIPTION_INDEX];
        this.taskDate = commands[Constants.TASK_DATE_INDEX];
    }

    /**
     * Adds a new todo task to task list. If userCommand does not contain task
     * description, throw invalid command syntax error.
     * Used for user input commands. 
     * 
     * @throws InvalidSyntaxException If userCommand is null.
     */
    public Todo executeAddTodo() throws InvalidSyntaxException {
        if (taskDescription == null) {
            throw new InvalidSyntaxException(Constants.MESSAGE_TODO_SYNTAX);
        } 
        return new Todo(taskDescription);
    }

    public Todo addTodoFromStorage() throws ImportTaskException {
        if (taskDescription == null) {
            throw new ImportTaskException();
        }

        return new Todo(taskDescription);
    }

    /**
     * Adds a new deadline task to task list. Processes the user input to extract
     * date. If date is not null, create new deadline task. Otherwise, throw invalid
     * command syntax error.
     * 
     * @throws IllegalArgumentException If date is not found.
     */
    public Deadline executeAddDeadline() throws InvalidSyntaxException {
        if (taskDescription == null | taskDate == null) {
            throw new InvalidSyntaxException(Constants.MESSAGE_DEADLINE_SYNTAX);
        }
        return new Deadline(taskDescription, taskDate);
    }

    public Deadline addDeadlineFromStorage() throws ImportTaskException {
        if (taskDescription == null | taskDate == null) {
            throw new ImportTaskException();
        }

        return new Deadline(taskDescription, taskDate);
    }

    /**
     * Adds a new deadline task to task list. 
     * Processes the user input to exit. 
     * If date is not null, create new deadline task.  
     * 
     * @throws IllegalArgumentException If date is not found.
     */
    public Event executeAddEvent() throws InvalidSyntaxException {
        if (taskDescription == null | taskDate == null) {
            throw new InvalidSyntaxException(Constants.MESSAGE_EVENT_SYNTAX);
        }
        return new Event(taskDescription, taskDate);
    }

    public Event addEventFromStorage() throws ImportTaskException {
        if (taskDescription == null | taskDate== null) {
            throw new ImportTaskException();
        }

        return new Event(taskDescription, taskDate);
    }
}
