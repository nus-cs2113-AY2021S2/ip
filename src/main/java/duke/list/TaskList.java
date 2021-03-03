package duke.list;

import duke.Command;
import duke.exceptions.EmptyDescriptionException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * Represents the entire task list. Contains the Tasks inputted by user.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty ArrayList to store Tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add a Task to the TaskList
     *
     * @param description description of a task
     * @param command user's command
     * @return user's command if valid task. Else, return error.
     */
    public Command addTask(String description, Command command) {
        Task newTask;
        String[] stringArray;
        try {
            Parser.validateDescription(description, command);
        } catch (EmptyDescriptionException e) {
            return Command.ADD_ERROR;
        }
        switch (command) {
        case TODO:
            newTask = new ToDo(description);
            break;
        case EVENT:
            stringArray = description.split("/at");
            try {
                newTask = new Event(stringArray[0], stringArray[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return Command.ERROR;
            }
            break;
        case DEADLINE:
            stringArray = description.split("/by");
            try {
                newTask = new Deadline(stringArray[0], stringArray[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return Command.ERROR;
            }
            break;
        default:
            return Command.ERROR;
        }
        tasks.add(newTask);
        return command;
    }

    /**
     * Get string representation of TaskList.
     *
     * @return a String containing all the information of all the Tasks
     */
    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            outputString.append(i + 1);
            outputString.append(".");
            outputString.append(tasks.get(i).toString());
            outputString.append(System.lineSeparator());
        }
        if (outputString.length() == 0) {
            return "List is empty!";
        }
        return outputString.toString().trim();
    }

    /**
     * Set Task as done, given index of the Task in TaskList.
     *
     * @param taskIndex index of Task in TaskList
     * @throws IndexOutOfBoundsException If taskIndex is out of range
     */
    public void finishTask(int taskIndex) throws IndexOutOfBoundsException{
        tasks.get(taskIndex).setDone();
    }

    /**
     * Set the last Task in TaskList as done.
     */
    public void finishLastTask() {
        tasks.get(tasks.size() - 1).setDone();
    }

    /**
     * Get a Task in TaskList given the index of Task
     *
     * @param taskIndex index of Task in TaskList
     * @return a Task in that index
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Get the last Task of TaskList
     *
     * @return the last Task in TaskList
     */
    public Task getLastTask() {
        return getTask(getSize() - 1);
    }

    /**
     * Get size of TaskList
     *
     * @return size of TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Get the to be deleted Task before deleting. Afterwards, delete the Task.
     *
     * @param taskIndex index of Task in TaskList
     * @return the deleted Task
     */
    public Task getDeletedTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        deleteTask(taskIndex);
        return task;
    }

    private void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    /**
     * Find all Tasks with corresponding keywords in the ArrayList
     *
     * @param keyword keyword to search for
     * @return an ArrayList of Tasks that contains the keyword
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
