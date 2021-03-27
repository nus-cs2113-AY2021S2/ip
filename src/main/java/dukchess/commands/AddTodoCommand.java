package dukchess.commands;

import dukchess.entity.Todo;

/**
 * Command for adding a new Todo
 */
public class AddTodoCommand extends Command {
    private static String addTodo(String description) {
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        return String.format("Gotcha, added this todo: %s", newTodo.toString());
    }

    /**
     * Performs input validation before adding a new to-do to the list of tasks.
     * @param todoDescription - the description for the to-do task
     */
    public static void handleAddTodo(String todoDescription) {
        if (todoDescription.length() == 0) {
            System.out.println("Oops, todo description cannot be empty :(");
            return;
        }
        String todoAdditionOutcome = addTodo(todoDescription);
        System.out.println(todoAdditionOutcome);
    }
}
