package command.parser;

import task.list.Deadline;
import task.list.Event;
import task.list.TaskList;
import task.list.Todo;

import java.util.ArrayList;

public class AddCommand extends Command {

    public static void addTaskInDeadlineList(String line, ArrayList<TaskList> tasks) {
        String description = getTaskDescription(line);
        Deadline newTask = new Deadline(description);
        tasks.add(newTask);
    }

    public static String getTaskDescription(String line) {
        String[] commandWords = (line.split(" ", NUMBER_OF_COMMAND_ARGUMENTS));
        return commandWords[DESCRIPTION_INDEX_IN_COMMANDS];
    }

    public static void addTaskInEventList(String line, ArrayList<TaskList> tasks) {
        String description = getTaskDescription(line);
        Event newTask = new Event(description);
        tasks.add(newTask);
    }

    public static void addTaskInTodoList(String line, ArrayList<TaskList> tasks) {
        String description = getTaskDescription(line);
        Todo newTask = new Todo(description);
        tasks.add(newTask);
    }
}