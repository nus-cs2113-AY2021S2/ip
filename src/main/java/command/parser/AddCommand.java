package command.parser;

import task.list.Deadline;
import task.list.Event;
import task.list.Task;
import task.list.Todo;

import java.util.ArrayList;

/**
 * Represents the add command
 */
public class AddCommand extends Command {

    /**
     * adds a deadline into the list
     *
     * @param line  is the inputted line
     * @param tasks is the list of tasks
     */
    public static void addTaskInDeadlineList(String line, ArrayList<Task> tasks) {
        String description = getTaskDescription(line);
        Deadline newTask = new Deadline(description);
        tasks.add(newTask);
    }

    /**
     * gets the task description from input line
     *
     * @param line is the inputted line
     * @return description attribute
     */
    public static String getTaskDescription(String line) {
        String[] commandWords = (line.split(" ", NUMBER_OF_COMMAND_ARGUMENTS));
        return commandWords[DESCRIPTION_INDEX_IN_COMMANDS];
    }

    /**
     * adds an event into the list
     *
     * @param line  is the inputted line
     * @param tasks is the list of tasks
     */
    public static void addTaskInEventList(String line, ArrayList<Task> tasks) {
        String description = getTaskDescription(line);
        Event newTask = new Event(description);
        tasks.add(newTask);
    }

    /**
     * adds a task to do in the list
     *
     * @param line  is the inputted line
     * @param tasks is the list of tasks
     */
    public static void addTaskInTodoList(String line, ArrayList<Task> tasks) {
        String description = getTaskDescription(line);
        Todo newTask = new Todo(description);
        tasks.add(newTask);
    }
}