package commands;

import task.Todo;

public class AddTodoFromFile extends Command {

    /**
     * add todo from save file
     * @param commandDone whether the todo is done or not
     * @param commandArgs description of the todo
     */
    public static void execute(String commandDone, String commandArgs) {
        Todo todo = new Todo(commandArgs);
        if (commandDone.equals("1")) {
            todo.markAsDone();
        }
        taskManager.addTask(todo);
    }
}
