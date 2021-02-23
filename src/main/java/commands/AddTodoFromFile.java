package commands;

import task.Todo;

public class AddTodoFromFile extends Command {

    public static void execute(String commandDone, String commandArgs) {
        Todo todo = new Todo(commandArgs);
        if (commandDone.equals("1")) {
            todo.markAsDone();
        }
        taskManager.addTask(todo);
    }
}
