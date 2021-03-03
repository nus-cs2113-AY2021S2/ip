package command;

import exception.TaskFormatException;
import exception.TodoFormatException;
import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * Represent a command of creating a new todo task and adding it to the task list
 */
public class AddTodoCommand extends Command{
    private String feedbackFormat;
    private ToDo newTodo;

    public AddTodoCommand(String todoName){
        newTodo = new ToDo(todoName, false);
    }

    /**
     * add the new todo to task list
     * @return the feedback message of execution
     * @throws TaskFormatException
     */
    @Override
    public CommandResult execute(){
        tasks.addTask(newTodo);
        feedbackFormat = "ToDo added:\n" + PRE_SPACE + PRE_SPACE + newTodo.getTaskInfo() + "\n" +
                PRE_SPACE + "Now you have " + tasks.getTaskCount() + " tasks in the list";
        return new CommandResult(feedbackFormat);
    }
}
