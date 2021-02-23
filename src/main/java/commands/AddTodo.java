package commands;

import task.Todo;
import exceptions.EmptyDescriptionException;

public class AddTodo extends Command{

    public static void execute(String commandArgs) throws EmptyDescriptionException {
        if (commandArgs.equals("")) {
            throw new EmptyDescriptionException();
        }
        Todo todo = new Todo(commandArgs);
        taskManager.addTask(todo);
        printAddTask(todo);
    }
}
