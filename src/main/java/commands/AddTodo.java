package commands;

import task.Todo;
import exceptions.EmptyDescriptionException;

public class AddTodo extends Command{

    /**
     * add todo and feedback display message when todo added
     * @param commandArgs description of the todo
     * @throws EmptyDescriptionException when description of the todo is empty
     */
    public static void execute(String commandArgs) throws EmptyDescriptionException {
        if (commandArgs.equals("")) {
            throw new EmptyDescriptionException();
        }
        Todo todo = new Todo(commandArgs);
        taskManager.addTask(todo);
        printAddTask(todo);
    }
}
