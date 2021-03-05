package command;

import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * Represent a command of creating a new todo task and adding it to the task list
 */
public class AddTodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    public static final String ERROR_MESSAGE = "Name of Todo should be specified after todo keyword.\n" + PRE_SPACE +
            "The syntax for adding a ToDo task is: todo <task name>    Eg. todo do homework";
    private String feedbackFormat;
    private ToDo newTodo;
    private final String FEEDBACK_FORMAT = "ToDo added:\n" + PRE_SPACE + PRE_SPACE + "%s\n" +
            PRE_SPACE + "Now you have %s tasks in the list";

    public AddTodoCommand(String todoName){
        newTodo = new ToDo(todoName, false);
    }

    /**
     * add the new todo to task list
     * @return the feedback message of execution
     */
    @Override
    public CommandResult execute(){
        tasks.addTask(newTodo);
        feedbackFormat = String.format(FEEDBACK_FORMAT, newTodo.getTaskInfo(), tasks.getTaskCount());
        return new CommandResult(feedbackFormat);
    }
}
